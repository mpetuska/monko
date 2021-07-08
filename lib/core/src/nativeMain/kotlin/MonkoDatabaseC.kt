package dev.petuska.monko.core

import dev.petuska.monko.core.bson.MonkoBson
import dev.petuska.monko.core.bson.MonkoBsonC
import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.ptr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mongoc.bson_error_t
import mongoc.bson_t
import mongoc.mongoc_client_get_database
import mongoc.mongoc_database_command_with_opts
import mongoc.mongoc_database_destroy
import mongoc.mongoc_database_t

internal class MonkoDatabaseC(
  override val client: MonkoClientC,
  override val dbName: String
) : MonkoDatabase {
  override val source: MongoDatabase = this
  internal val c: CPointer<mongoc_database_t> = mongoc_client_get_database(client.c, dbName)!!

  override suspend fun collection(name: String): MonkoCollection<Document> = MonkoCollectionC(this, name)

  override fun close() {
    mongoc_database_destroy(c)
  }

  override suspend fun runCommand(command: MonkoBson): MonkoBson = withContext(Dispatchers.Default) {
    val reply = nativeHeap.alloc<bson_t>().ptr
    memScoped {
      val error = alloc<bson_error_t>().ptr

      if (mongoc_database_command_with_opts(
          c,
          command.bson,
          null,
          null,
          reply,
          null,
        )
      ) {
        MonkoBsonC(reply)
      } else {
        throw IllegalStateException("Command failed - $error")
      }
    }
  }
}
