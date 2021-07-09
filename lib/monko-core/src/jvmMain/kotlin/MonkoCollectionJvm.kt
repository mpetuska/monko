package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoCollection
import dev.petuska.monko.core.util.noop

internal class MonkoCollectionJvm<T>(
  override val database: MonkoDatabaseJvm,
  override val name: String,
  override val source: MongoCollection<T>,
) : MonkoCollection<T>, MongoCollection<T> by source {
  override fun close() = noop
}
