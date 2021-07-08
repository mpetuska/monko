package dev.petuska.monko.core.ext

import kotlinx.cinterop.CPointer
import mongoc.bson_t

public actual open class Document : Bson {
  override val bson: CPointer<bson_t>
    get() = TODO("Not yet implemented")
}
