package dev.petuska.monko.core.ext

import kotlin.js.Promise

@JsModule("mongodb")
public actual external interface MongoClient {
  public fun db(name: String, options: dynamic = definedExternally): MongoDatabase
  public fun close(): dynamic
}

@JsModule("mongodb")
@JsName("MongoClient")
public external object MongodbModule {
  public class MongoClient : dev.petuska.monko.core.ext.MongoClient {
    public companion object {
      public fun connect(url: String, options: dynamic = definedExternally): Promise<MongoClient>
    }

    override fun db(name: String, options: dynamic): MongoDatabase

    override fun close(): dynamic
  }
}
