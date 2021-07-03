package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoClient
import dev.petuska.monko.core.util.Closeable
import dev.petuska.monko.core.util.Proxy

public interface MonkoClient : Proxy<MongoClient>, MongoClient, Closeable {
  public fun database(name: String): MonkoDatabase
}

public expect suspend fun MonkoClient(
  connectionString: String,
  connectionStringJS: String? = null,
  connectionStringJVM: String? = null,
  connectionStringNative: String? = null,
): MonkoClient
