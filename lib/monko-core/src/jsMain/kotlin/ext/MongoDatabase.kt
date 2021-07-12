package dev.petuska.monko.core.ext

import kotlin.js.Json
import kotlin.js.Promise

@JsModule("mongodb")
@JsName("Db")
public actual external interface MongoDatabase {
  public fun collection(name: String, options: dynamic = definedExternally): MongoCollection<dynamic>
  public fun command(command: Json, options: dynamic = definedExternally): Promise<Json>
}
