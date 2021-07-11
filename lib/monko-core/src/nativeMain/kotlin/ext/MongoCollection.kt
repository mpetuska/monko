package dev.petuska.monko.core.ext

import dev.petuska.monko.core.util.NativeProxy
import mongoc.mongoc_collection_t

public actual interface MongoCollection<T> : NativeProxy<mongoc_collection_t>
