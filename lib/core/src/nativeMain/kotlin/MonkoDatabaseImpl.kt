package dev.petuska.monko.core

import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase
import kotlinx.cinterop.CPointer
import mongoc.mongoc_client_get_database
import mongoc.mongoc_client_t
import mongoc.mongoc_database_destroy
import mongoc.mongoc_database_t

internal class MonkoDatabaseImpl(
  private val client: CPointer<mongoc_client_t>,
  private val name: String
) : MonkoDatabase {
  override val source: MongoDatabase = this
  private val database: CPointer<mongoc_database_t> = mongoc_client_get_database(client, name)!!

  override fun collection(name: String): MonkoCollection<Document> = MonkoCollectionImpl(client, this.name, name)

  override fun close() {
    mongoc_database_destroy(database)
  }
}
