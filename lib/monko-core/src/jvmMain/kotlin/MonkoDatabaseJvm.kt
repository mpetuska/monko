package dev.petuska.monko.core

import dev.petuska.monko.core.bson.MonkoBson
import dev.petuska.monko.core.bson.MonkoBsonJvm
import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase
import dev.petuska.monko.core.util.noop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.withContext

internal class MonkoDatabaseJvm(
  override val client: MonkoClientJvm,
  override val source: MongoDatabase,
) : MonkoDatabase {
  override val name: String
    get() = source.name

  override fun close() = noop

  override suspend fun collection(name: String): MonkoCollection<Document> =
    MonkoCollectionJvm(this, name, source.getCollection(name))

  override suspend fun runCommand(command: MonkoBson): MonkoBson = withContext(Dispatchers.Default) {
    MonkoBsonJvm(source.runCommand(command).awaitFirst())
  }
}
