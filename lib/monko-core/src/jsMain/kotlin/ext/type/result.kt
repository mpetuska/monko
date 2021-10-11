package dev.petuska.monko.core.ext.type

import kotlin.js.Json

public external interface InsertOneResult : Json {
  public var acknowledged: Boolean? get() = definedExternally; set(_) = definedExternally
  public var insertedId: dynamic get() = definedExternally; set(_) = definedExternally
}
