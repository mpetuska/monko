package dev.petuska.monko.core.util

import dev.petuska.monko.core.bson.MonkoBsonErrorC
import kotlinx.cinterop.CPointer
import mongoc.bson_error_t

public class MonkoCommandException internal constructor(command: String, error: MonkoBsonErrorC) :
  IllegalStateException("Monko Command[$command] Failed: ${error.toJson()}") {
  internal constructor(command: String, error: CPointer<bson_error_t>) : this(command, MonkoBsonErrorC(error))
}
