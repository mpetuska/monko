package dev.petuska.monko.core.bson

import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.util.noop
import org.bson.BsonDocument

internal class MonkoBsonJvm(override val source: Bson) : MonkoBson, Bson by source {
  private val bsonDocument: BsonDocument by lazy { source.toBsonDocument() }
  override fun toJson(): String = bsonDocument.toJson()
  override fun close() = noop
}

public actual fun bsonOf(json: String): MonkoBson = MonkoBsonJvm(BsonDocument.parse(json))
