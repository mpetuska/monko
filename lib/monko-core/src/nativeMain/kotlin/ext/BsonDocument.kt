package dev.petuska.monko.core.ext

import kotlinx.cinterop.CPointer
import mongoc.bson_t

public actual open class BsonDocument(override val c: CPointer<bson_t>) : Bson
