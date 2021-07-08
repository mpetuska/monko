package dev.petuska.monko.core.bson

import dev.petuska.monko.core.ext.Bson
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKStringFromUtf8
import kotlinx.cinterop.utf8
import mongoc.bson_as_relaxed_extended_json
import mongoc.bson_destroy
import mongoc.bson_new_from_json
import mongoc.bson_t

internal class MonkoBsonC(override val bson: CPointer<bson_t>) : MonkoBson {
  override val source: Bson = this
  override fun toJson(): String {
    return bson_as_relaxed_extended_json(bson, null)?.toKStringFromUtf8()
      ?: throw IllegalStateException("Unable to create JSON from given BSON - $this")
  }

  override fun close() {
    bson_destroy(bson)
  }
}

public actual fun bsonOf(json: String): MonkoBson {
  return memScoped {
    val cBson = bson_new_from_json(json.utf8.getPointer(this).reinterpret(), -1, null)!!
    MonkoBsonC(cBson)
  }
}
