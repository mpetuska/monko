package dev.petuska.monko.core

import dev.petuska.monko.core.ext.Bson
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

  override suspend fun countDocuments(query: Bson): Long = withContext(Dispatchers.Default) {
    source.countDocuments(query).await().toLong()
  }
}
