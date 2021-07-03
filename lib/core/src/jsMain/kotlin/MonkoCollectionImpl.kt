package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoCollection

internal class MonkoCollectionImpl<T>(
  override val source: MongoCollection<T>,
) : MonkoCollection<T>, MongoCollection<T> by source {
  override fun close() {
    TODO("Not yet implemented")
  }
}
