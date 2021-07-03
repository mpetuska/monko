package dev.petuska.monko.core

import dev.petuska.monko.core.ext.Document
import dev.petuska.monko.core.ext.MongoDatabase
import dev.petuska.monko.core.util.noop

internal class MonkoDatabaseImpl(
  override val source: MongoDatabase
) : MonkoDatabase, MongoDatabase by source {
  override fun collection(name: String): MonkoCollection<Document> = MonkoCollectionImpl(source.getCollection(name))

  override fun close() = noop
}
