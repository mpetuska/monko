package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoCollection
import kotlinx.cinterop.CPointer
import mongoc.mongoc_client_get_collection
import mongoc.mongoc_client_t
import mongoc.mongoc_collection_destroy
import mongoc.mongoc_collection_t

internal class MonkoCollectionImpl<T>(
  client: CPointer<mongoc_client_t>,
  db: String,
  name: String
) : MonkoCollection<T> {
  override val source: MongoCollection<T> = this
  private val collection: CPointer<mongoc_collection_t> = mongoc_client_get_collection(client, db, name)!!

  override fun close() {
    mongoc_collection_destroy(collection)
  }
}
