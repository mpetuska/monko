package dev.petuska.monko.core

import dev.petuska.monko.core.bson.MonkoBson
import dev.petuska.monko.core.ext.MongoCollection
import dev.petuska.monko.core.util.noop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext

internal class MonkoCollectionJs<T>(
  override val database: MonkoDatabase,
  override val name: String,
  override val source: MongoCollection<T>,
) : MonkoCollection<T> {
  override fun close() = noop

  override suspend fun countDocuments(query: MonkoBson): Long = withContext(Dispatchers.Default) {
    source.countDocuments(query).await().toLong()
  }

  override suspend fun insertOne(document: T): Unit = withContext(Dispatchers.Default) {
    source.insertOne(document).await()
  }

  override suspend fun drop(): Boolean = withContext(Dispatchers.Default) {
    source.drop().await()
  }

  override suspend fun findOne(filter: MonkoBson): T = withContext(Dispatchers.Default) {
    source.findOne(filter).await()
  }
}
