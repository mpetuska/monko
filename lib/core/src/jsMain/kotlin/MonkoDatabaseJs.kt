package dev.petuska.monko.core

import dev.petuska.monko.core.bson.MonkoBson
import dev.petuska.monko.core.bson.MonkoBsonJs
import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase
import dev.petuska.monko.core.util.noop
import kotlinx.coroutines.await

internal class MonkoDatabaseJs(
  override val client: MonkoClient,
  override val dbName: String,
  override val source: MongoDatabase,
) : MonkoDatabase, MongoDatabase by source {
  override suspend fun collection(name: String): MonkoCollection<Document> =
    MonkoCollectionJs(this, name, source.collection(name))

  override fun close() = noop

  override suspend fun runCommand(command: MonkoBson): MonkoBson = MonkoBsonJs(source.command(command.source).await())
}
