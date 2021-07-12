package dev.petuska.monko.core.ext

import kotlin.js.Json
import kotlin.js.Promise

@JsModule("mongodb")
@JsName("Collection")
public actual external interface MongoCollection<T> {
  public fun countDocuments(query: Json, options: CountDocumentsOptions = definedExternally): Promise<Int>
}

public external interface CountDocumentsOptions : Json {
  public var collation: Json? get() = definedExternally; set(_) = definedExternally
  public var hint: dynamic get() = definedExternally; set(_) = definedExternally
  public var limit: Int? get() = definedExternally; set(_) = definedExternally
  public var skip: Int? get() = definedExternally; set(_) = definedExternally
  public var maxTimeMS: Int? get() = definedExternally; set(_) = definedExternally
}
