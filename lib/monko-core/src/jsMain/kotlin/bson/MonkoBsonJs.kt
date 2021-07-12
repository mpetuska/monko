package dev.petuska.monko.core.bson

import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.util.noop

internal class MonkoBsonJs(override val source: Bson) : MonkoBson, Bson by source {
  override fun toJson(): String = JSON.stringify(source)
  override fun close() = noop
}

public actual fun bsonOf(json: String): MonkoBson = MonkoBsonJs(JSON.parse(json))
