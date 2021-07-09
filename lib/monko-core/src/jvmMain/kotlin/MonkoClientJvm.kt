package dev.petuska.monko.core

import com.mongodb.reactivestreams.client.MongoClients
import dev.petuska.monko.core.ext.MongoClient
import dev.petuska.monko.core.util.noop

public actual suspend fun MonkoClient(
  connectionString: String,
  connectionStringJS: String?,
  connectionStringJVM: String?,
  connectionStringNative: String?,
): MonkoClient {
  return MongoClients.create(connectionStringJVM ?: connectionString).let(::MonkoClientJvm)
}

internal class MonkoClientJvm(override val source: MongoClient) : MonkoClient, MongoClient by source {
  override suspend fun database(name: String): MonkoDatabase = MonkoDatabaseJvm(this, getDatabase(name))
}

public actual suspend fun MonkoClient.Companion.cleanup(): Unit = noop
