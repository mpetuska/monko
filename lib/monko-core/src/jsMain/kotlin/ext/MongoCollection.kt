package dev.petuska.monko.core.ext

import dev.petuska.monko.core.domain.result.InsertOneResult
import dev.petuska.monko.core.ext.type.CommandOperationOptions
import dev.petuska.monko.core.ext.type.CountDocumentsOptions
import dev.petuska.monko.core.ext.type.FindOneOptions
import dev.petuska.monko.core.ext.type.InsertOneOptions
import kotlin.js.Json
import kotlin.js.Promise

@JsModule("mongodb")
@JsName("Collection")
public actual external interface MongoCollection<T> {
  public fun countDocuments(query: Json, options: CountDocumentsOptions? = definedExternally): Promise<Int>
  public fun insertOne(document: T, options: InsertOneOptions? = definedExternally): Promise<InsertOneResult>
  public fun drop(options: CommandOperationOptions? = definedExternally): Promise<Boolean>
  public fun findOne(filter: Bson = definedExternally, options: FindOneOptions? = definedExternally): Promise<T>
}
