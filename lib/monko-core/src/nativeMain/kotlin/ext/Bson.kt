package dev.petuska.monko.core.ext

import dev.petuska.monko.core.util.NativeProxy
import mongoc.bson_t

public actual interface Bson : NativeProxy<bson_t>
