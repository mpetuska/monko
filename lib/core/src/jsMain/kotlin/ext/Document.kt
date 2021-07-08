package dev.petuska.monko.core.ext

public actual open external class Document : Bson {
  override fun get(propertyName: String): Any? = definedExternally

  override fun set(propertyName: String, value: Any?): Unit = definedExternally
}
