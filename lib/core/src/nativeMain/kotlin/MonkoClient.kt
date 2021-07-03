package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoClient
import kotlinx.cinterop.CPointer
import mongoc.mongoc_cleanup
import mongoc.mongoc_client_destroy
import mongoc.mongoc_client_new_from_uri
import mongoc.mongoc_client_t
import mongoc.mongoc_init
import mongoc.mongoc_uri_destroy
import mongoc.mongoc_uri_new
import mongoc.mongoc_uri_t

public actual suspend fun MonkoClient(
  connectionString: String,
  connectionStringJS: String?,
  connectionStringJVM: String?,
  connectionStringNative: String?
): MonkoClient = MonkoClientImpl(connectionStringNative ?: connectionString)

internal class MonkoClientImpl(connectionString: String) : MonkoClient {
  override val source: MongoClient = this
  private val uri: CPointer<mongoc_uri_t>
  private val client: CPointer<mongoc_client_t>

  init {
    mongoc_init()
    uri = mongoc_uri_new(connectionString)!!
    client = mongoc_client_new_from_uri(uri)!!
  }

  override fun database(name: String): MonkoDatabase = MonkoDatabaseImpl(client, name)

  override fun close() {
    mongoc_uri_destroy(uri)
    mongoc_client_destroy(client)
    mongoc_cleanup()
  }
}
