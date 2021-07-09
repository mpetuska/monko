package dev.petuska.monko.core.ext

import kotlinx.cinterop.CPointer
import mongoc.bson_t

public actual interface Bson {
  public val bson: CPointer<bson_t>
}
