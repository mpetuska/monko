package dev.petuska.monko.core

import dev.petuska.monko.core.bson.MonkoBson
import dev.petuska.monko.core.bson.bsonOf
import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase
import dev.petuska.monko.core.util.NativeCloseable
import dev.petuska.monko.core.util.Proxy

public interface MonkoDatabase : Proxy<MongoDatabase>, NativeCloseable {
  public val name: String
  public val client: MonkoClient
  public suspend fun collection(name: String): MonkoCollection<Document>
  public suspend fun runCommand(command: MonkoBson): MonkoBson
}

public suspend fun MonkoDatabase.runCommand(command: String): MonkoBson = runCommand(bsonOf(command))
