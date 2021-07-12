package dev.petuska.monko.core.bson

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKStringFromUtf8
import kotlinx.cinterop.utf8
import mongoc.bson_as_relaxed_extended_json
import mongoc.bson_destroy
import mongoc.bson_free
import mongoc.bson_new_from_json
import mongoc.bson_t

internal class MonkoBsonC(override val c: CPointer<bson_t>) : MonkoBson {
  override val source: MonkoBson = this
  override fun toJson(): String {
    val cJson = bson_as_relaxed_extended_json(c, null)
      ?: throw IllegalStateException("Unable to create JSON from given BSON - $this")
    val kJson = cJson.toKStringFromUtf8()
    bson_free(cJson)
    return kJson
  }

  override fun close() {
    bson_destroy(c)
  }
}

public actual fun bsonOf(json: String): MonkoBson {
  return memScoped {
    val cBson = bson_new_from_json(json.utf8.getPointer(this).reinterpret(), -1, null)!!
    MonkoBsonC(cBson)
  }
}

public fun CPointer<bson_t>.extractJsonAndClose(): String = MonkoBsonC(this).run {
  toJson().also { close() }
}
