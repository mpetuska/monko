package dev.petuska.monko.core

import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase

internal class MonkoDatabaseImpl(
  override val source: MongoDatabase
) : MonkoDatabase, MongoDatabase by source {
  override fun collection(name: String): MonkoCollection<Document> {
    TODO("Not yet implemented")
  }

  override fun close() {
    TODO("Not yet implemented")
  }
}
