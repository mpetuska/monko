package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoClient
import dev.petuska.monko.core.util.MPPValue
import dev.petuska.monko.core.util.invoke
import kotlinx.cinterop.CPointer
import mongoc.mongoc_cleanup
import mongoc.mongoc_client_destroy
import mongoc.mongoc_client_new_from_uri
import mongoc.mongoc_client_t
import mongoc.mongoc_init
import mongoc.mongoc_uri_destroy
import mongoc.mongoc_uri_new
import mongoc.mongoc_uri_t

public actual suspend fun MonkoClient(connectionString: MPPValue<String>): MonkoClient =
  MonkoClientC(connectionString())

public actual suspend fun MonkoClient.Companion.cleanup() {
  mongoc_cleanup()
}

internal class MonkoClientC(connectionString: String) : MonkoClient, MongoClient {
  companion object {
    init {
      mongoc_init()
    }
  }

  private val uri: CPointer<mongoc_uri_t>
  override val source: MongoClient = this
  override val c: CPointer<mongoc_client_t>

  init {
    Companion
    uri = mongoc_uri_new(connectionString)!!
    c = mongoc_client_new_from_uri(uri)!!
  }

  override suspend fun database(name: String): MonkoDatabase = MonkoDatabaseC(this, name)

  override fun close() {
    mongoc_uri_destroy(uri)
    mongoc_client_destroy(c)
  }
}
