package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoClient
import dev.petuska.monko.core.util.Closeable
import dev.petuska.monko.core.util.MPPValue
import dev.petuska.monko.core.util.Proxy

public interface MonkoClient : Proxy<MongoClient>, Closeable {
  public suspend fun database(name: String): MonkoDatabase

  public companion object
}

public expect suspend fun MonkoClient.Companion.cleanup()

public suspend fun MonkoClient(
  connectionString: String
): MonkoClient = MonkoClient(MPPValue(connectionString))

public expect suspend fun MonkoClient(connectionString: MPPValue<String>): MonkoClient
