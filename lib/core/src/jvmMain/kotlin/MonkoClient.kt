package dev.petuska.monko.core

import com.mongodb.reactivestreams.client.MongoClients
import dev.petuska.monko.core.ext.MongoClient

public actual suspend fun MonkoClient(
  connectionString: String,
  connectionStringJS: String?,
  connectionStringJVM: String?,
  connectionStringNative: String?,
): MonkoClient {
  return MongoClients.create(connectionStringJVM ?: connectionString).let(::MonkoClientImpl)
}

internal class MonkoClientImpl(override val source: MongoClient) : MonkoClient, MongoClient by source {
  override fun database(name: String): MonkoDatabase = MonkoDatabaseImpl(getDatabase(name))
}
