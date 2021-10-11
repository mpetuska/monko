package dev.petuska.monko.core.ext.type

import dev.petuska.monko.core.ext.Document
import kotlin.js.Json

public external interface CollationOptions : Json {
  public var alternate: String? get() = definedExternally; set(_) = definedExternally
  public var backwards: Boolean? get() = definedExternally; set(_) = definedExternally
  public var caseFirst: String? get() = definedExternally; set(_) = definedExternally
  public var caseLevel: Boolean? get() = definedExternally; set(_) = definedExternally
  public var locale: String? get() = definedExternally; set(_) = definedExternally
  public var maxVariable: String? get() = definedExternally; set(_) = definedExternally
  public var normalization: Boolean? get() = definedExternally; set(_) = definedExternally
  public var numericOrdering: Boolean? get() = definedExternally; set(_) = definedExternally
  public var strength: Number? get() = definedExternally; set(_) = definedExternally
}

public external interface CommandOperationOptions : Json {
  public var authdb: String? get() = definedExternally; set(_) = definedExternally
  public var collation: CollationOptions? get() = definedExternally; set(_) = definedExternally
  public var comment: dynamic get() = definedExternally; set(_) = definedExternally
  public var dbName: String? get() = definedExternally; set(_) = definedExternally
  public var fullResponse: Boolean? get() = definedExternally; set(_) = definedExternally
  public var maxTimeMS: Int? get() = definedExternally; set(_) = definedExternally
  public var noResponse: Boolean? get() = definedExternally; set(_) = definedExternally
  public var readConcern: dynamic get() = definedExternally; set(_) = definedExternally
  public var retryWrites: Boolean? get() = definedExternally; set(_) = definedExternally
}

public external interface AggregateOptions : CommandOperationOptions {
  public var allowDiskUse: Boolean? get() = definedExternally; set(_) = definedExternally
  public var batchSize: Number? get() = definedExternally; set(_) = definedExternally
  public var bypassDocumentValidation: Number? get() = definedExternally; set(_) = definedExternally
  public var cursor: Document? get() = definedExternally; set(_) = definedExternally
  public var hint: dynamic get() = definedExternally; set(_) = definedExternally
  public var let: Document? get() = definedExternally; set(_) = definedExternally
  public var maxAwaitTimeMS: Int? get() = definedExternally; set(_) = definedExternally
  public var out: String? get() = definedExternally; set(_) = definedExternally
}

public external interface CountDocumentsOptions : AggregateOptions {
  public var limit: Int? get() = definedExternally; set(_) = definedExternally
  public var skip: Int? get() = definedExternally; set(_) = definedExternally
}

public external interface InsertOneOptions : CommandOperationOptions {
  public var bypassDocumentValidation: Boolean? get() = definedExternally; set(_) = definedExternally
  public var forceServerObjectId: Boolean? get() = definedExternally; set(_) = definedExternally
}

public external interface FindOneOptions : CommandOperationOptions {
  public var allowDiskUse: Boolean? get() = definedExternally; set(_) = definedExternally
  public var allowPartialResults: Boolean? get() = definedExternally; set(_) = definedExternally
  public var awaitData: Boolean? get() = definedExternally; set(_) = definedExternally
  public var batchSize: Int? get() = definedExternally; set(_) = definedExternally
  public var hint: dynamic get() = definedExternally; set(_) = definedExternally
  public var let: Document? get() = definedExternally; set(_) = definedExternally
  public var limit: Int? get() = definedExternally; set(_) = definedExternally
  public var max: Document? get() = definedExternally; set(_) = definedExternally
  public var maxAwaitTimeMS: Int? get() = definedExternally; set(_) = definedExternally
  public var min: Document? get() = definedExternally; set(_) = definedExternally
  public var noCursorTimeout: Boolean? get() = definedExternally; set(_) = definedExternally
  public var projection: Document? get() = definedExternally; set(_) = definedExternally
  public var returnKey: Boolean? get() = definedExternally; set(_) = definedExternally
  public var showRecordId: Boolean? get() = definedExternally; set(_) = definedExternally
  public var singleBatch: Boolean? get() = definedExternally; set(_) = definedExternally
  public var skip: Int? get() = definedExternally; set(_) = definedExternally
  public var sort: dynamic? get() = definedExternally; set(_) = definedExternally
  public var tailable: Boolean? get() = definedExternally; set(_) = definedExternally
  public var timeout: Boolean? get() = definedExternally; set(_) = definedExternally
}
