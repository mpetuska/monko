package dev.petuska.monko.core

import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase
import dev.petuska.monko.core.util.NativeCloseable
import dev.petuska.monko.core.util.Proxy

public interface MonkoDatabase : Proxy<MongoDatabase>, MongoDatabase, NativeCloseable {
  public fun collection(name: String): MonkoCollection<Document>
}
