package dev.petuska.monko.core.bson

import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.util.noop
import org.bson.BsonDocument

internal class MonkoBsonJvm(override val source: Bson) : MonkoBson, Bson by source {
  private val bsonDocument: BsonDocument by lazy { source.toBsonDocument() }
  override fun toJson(): String = bsonDocument.toJson()
  override fun close() = noop
  override fun toString(): String = toJson()
}

public actual fun bsonOf(json: String): MonkoBson = MonkoBsonJvm(BsonDocument.parse(json))
public actual fun MonkoBson.toDocument(): Document = Document.parse(toJson())
