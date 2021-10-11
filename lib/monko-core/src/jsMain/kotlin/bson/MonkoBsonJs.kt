package dev.petuska.monko.core.bson

import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.util.noop

internal class MonkoBsonJs(override val source: Bson) : MonkoBson, Bson by source {
  override fun toJson(): String = JSON.stringify(source)
  override fun close() = noop
  override fun toString(): String = toJson()
}

public actual fun bsonOf(json: String): MonkoBson = MonkoBsonJs(JSON.parse(json))

public actual fun MonkoBson.toDocument(): Document = unsafeCast<Document>()
