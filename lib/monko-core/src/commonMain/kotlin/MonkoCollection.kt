package dev.petuska.monko.core

import dev.petuska.monko.core.bson.EmptyBson
import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.ext.MongoCollection
import dev.petuska.monko.core.util.NativeCloseable
import dev.petuska.monko.core.util.Proxy

public interface MonkoCollection<T> : Proxy<MongoCollection<T>>, NativeCloseable {
  public val name: String
  public val database: MonkoDatabase

  public suspend fun countDocuments(query: Bson = EmptyBson): Long
}
