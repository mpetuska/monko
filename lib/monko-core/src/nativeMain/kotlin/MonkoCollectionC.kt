package dev.petuska.monko.core

import dev.petuska.monko.core.bson.MonkoBson
import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.ext.MongoCollection
import dev.petuska.monko.core.util.MonkoCommandException
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mongoc.bson_error_t
import mongoc.bson_t
import mongoc.mongoc_client_get_collection
import mongoc.mongoc_collection_count_documents
import mongoc.mongoc_collection_destroy
import mongoc.mongoc_collection_drop
import mongoc.mongoc_collection_insert_one
import mongoc.mongoc_collection_t

internal class MonkoCollectionC<T : Bson>(
  override val database: MonkoDatabaseC,
  override val name: String,
) : MonkoCollection<T>, MongoCollection<T> {
  override val source: MongoCollection<T> = this
  override val c: CPointer<mongoc_collection_t> =
    mongoc_client_get_collection(database.client.c, database.name, name)!!

  override fun close() {
    mongoc_collection_destroy(c)
  }

  override suspend fun countDocuments(query: MonkoBson): Long = withContext(Dispatchers.Default) {
    memScoped {
      val reply = alloc<bson_t>().ptr
      val error = alloc<bson_error_t>().ptr

      val count = mongoc_collection_count_documents(
        coll = c,
        filter = query.c,
        opts = null,
        read_prefs = null,
        reply = reply,
        error = error,
      )
      if (count < 0) {
        throw MonkoCommandException("countDocuments", error)
      } else {
        count
      }
    }
  }

  override suspend fun insertOne(document: T) {
    memScoped {
      val reply = alloc<bson_t>().ptr
      val error = alloc<bson_error_t>().ptr

      val success = mongoc_collection_insert_one(
        collection = c,
        document = document.c,
        opts = null,
        reply = reply,
        error = error,
      )
      if (!success) {
        throw MonkoCommandException("insertOne", error)
      }
    }
  }

  override suspend fun drop(): Boolean {
    memScoped {
      val error = alloc<bson_error_t>().ptr

      val success = mongoc_collection_drop(
        collection = c,
        error = error,
      )
      if (!success) {
        println(MonkoCommandException("drop", error).message)
      }
      return success
    }
  }
}
