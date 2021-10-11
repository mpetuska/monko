package dev.petuska.monko.core

import dev.petuska.monko.core.bson.MonkoBson
import dev.petuska.monko.core.ext.MongoCollection
import dev.petuska.monko.core.util.noop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.withContext

internal class MonkoCollectionJvm<T>(
  override val database: MonkoDatabaseJvm,
  override val name: String,
  override val source: MongoCollection<T>,
) : MonkoCollection<T> {
  override fun close() = noop

  override suspend fun countDocuments(query: MonkoBson): Long = withContext(Dispatchers.IO) {
    source.countDocuments(query).awaitFirst()
  }

  override suspend fun insertOne(document: T): Unit = withContext(Dispatchers.IO) {
    source.insertOne(document).awaitFirst()
  }

  override suspend fun drop(): Boolean = withContext(Dispatchers.IO) {
    try {
      source.drop().awaitFirstOrNull()
      true
    } catch (e: Error) {
      false
    }
  }

  suspend fun findOne(filter: MonkoBson): T = withContext(Dispatchers.IO) {
    source.find(filter).awaitSingle()
  }
}
