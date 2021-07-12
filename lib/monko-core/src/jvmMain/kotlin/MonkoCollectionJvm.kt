package dev.petuska.monko.core

import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.ext.MongoCollection
import dev.petuska.monko.core.util.noop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.withContext

internal class MonkoCollectionJvm<T>(
  override val database: MonkoDatabaseJvm,
  override val name: String,
  override val source: MongoCollection<T>,
) : MonkoCollection<T> {
  override fun close() = noop

  override suspend fun countDocuments(query: Bson): Long = withContext(Dispatchers.Default) {
    source.countDocuments(query).awaitFirst()
  }
}
