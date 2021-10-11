package dev.petuska.monko.core

import com.mongodb.reactivestreams.client.MongoClients
import dev.petuska.monko.core.ext.MongoClient
import dev.petuska.monko.core.util.MPPValue
import dev.petuska.monko.core.util.invoke
import dev.petuska.monko.core.util.noop

public actual suspend fun MonkoClient(connectionString: MPPValue<String>): MonkoClient {
  return MongoClients.create(connectionString()).let(::MonkoClientJvm)
}

internal class MonkoClientJvm(override val source: MongoClient) : MonkoClient {
  override fun close() = source.close()

  override suspend fun database(name: String): MonkoDatabase = MonkoDatabaseJvm(this, source.getDatabase(name))
}

public actual suspend fun MonkoClient.Companion.cleanup(): Unit = noop
