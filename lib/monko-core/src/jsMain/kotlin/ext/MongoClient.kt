package dev.petuska.monko.core.ext

import kotlin.js.Promise

@JsModule("mongodb")
public actual external interface MongoClient {
  public fun db(name: String, options: dynamic = definedExternally): MongoDatabase
  public fun close(): dynamic
}

@JsModule("mongodb")
public external fun connect(url: String, options: dynamic = definedExternally): Promise<MongoClient>
