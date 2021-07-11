package dev.petuska.monko.core.bson

import dev.petuska.monko.core.ext.Bson
import dev.petuska.monko.core.util.NativeCloseable
import dev.petuska.monko.core.util.Proxy

public interface MonkoBson : Proxy<Bson>, Bson, NativeCloseable {
  public fun toJson(): String
}

public expect fun bsonOf(json: String): MonkoBson

public val EmptyBson: Bson = bsonOf("{}")
