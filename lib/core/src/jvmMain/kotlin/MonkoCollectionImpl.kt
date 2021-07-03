package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoCollection
import dev.petuska.monko.core.util.noop

internal class MonkoCollectionImpl<T>(
  override val source: MongoCollection<T>
) : MonkoCollection<T>, MongoCollection<T> by source {
  override fun close() = noop
}
