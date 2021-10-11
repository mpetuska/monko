package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoClient
import dev.petuska.monko.core.ext.MongodbModule
import dev.petuska.monko.core.util.MPPValue
import dev.petuska.monko.core.util.invoke
import dev.petuska.monko.core.util.noop
import kotlinx.coroutines.await

public actual suspend fun MonkoClient(connectionString: MPPValue<String>): MonkoClient =
  MongodbModule.MongoClient.connect(connectionString()).await().let(::MonkoClientJs)

internal class MonkoClientJs(override val source: MongoClient) : MonkoClient {
  override suspend fun database(name: String): MonkoDatabase = MonkoDatabaseJs(this, name, source.db(name))

  override fun close() = source.close()
}

public actual suspend fun MonkoClient.Companion.cleanup(): Unit = noop
