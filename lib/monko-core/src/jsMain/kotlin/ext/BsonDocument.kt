package dev.petuska.monko.core.ext

@JsModule("mongodb")
public actual open external class BsonDocument : Bson {
  override fun get(propertyName: String): Any? = definedExternally
  override fun set(propertyName: String, value: Any?): Unit = definedExternally
}
