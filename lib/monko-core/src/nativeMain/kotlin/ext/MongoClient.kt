package dev.petuska.monko.core.ext

import dev.petuska.monko.core.util.NativeProxy
import mongoc.mongoc_client_t

public actual interface MongoClient : NativeProxy<mongoc_client_t>
