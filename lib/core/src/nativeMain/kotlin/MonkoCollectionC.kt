package dev.petuska.monko.core

import dev.petuska.monko.core.ext.MongoCollection
import kotlinx.cinterop.CPointer
import mongoc.mongoc_client_get_collection
import mongoc.mongoc_collection_destroy
import mongoc.mongoc_collection_t

internal class MonkoCollectionC<T>(
  override val database: MonkoDatabaseC,
  override val name: String,
) : MonkoCollection<T> {
  override val source: MongoCollection<T> = this
  private val collection: CPointer<mongoc_collection_t> = mongoc_client_get_collection(database.client.c, database.dbName, name)!!

  override fun close() {
    mongoc_collection_destroy(collection)
  }
}
