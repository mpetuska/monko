package dev.petuska.monko.core.config

import local.test.Environment
import local.test.getValue
import local.test.or

val MONGO_URL by Environment or "mongodb://localhost:27017"
val PLATFORM by Environment
