package dev.petuska.monko.core.ext

import dev.petuska.monko.core.util.NativeProxy
import mongoc.mongoc_database_t

public actual interface MongoDatabase : NativeProxy<mongoc_database_t>
