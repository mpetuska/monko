package dev.petuska.monko.core

import dev.petuska.monko.core.config.MONGO_URL
import dev.petuska.monko.core.ext.Document
import local.test.BlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MonkoCollectionTest : BlockingTest {
  private lateinit var client: MonkoClient
  private lateinit var database: MonkoDatabase
  private lateinit var collection: MonkoCollection<Document>

  override suspend fun beforeEach() {
    client = MonkoClient(MONGO_URL)
    database = client.database("test-db")
    collection = database.collection("test-collection")
  }

  override suspend fun afterEach() {
    collection.close()
    database.close()
    client.close()
  }

  @Test
  fun countDocuments() = blockingTest {
    assertEquals(0, collection.countDocuments())
  }
}
