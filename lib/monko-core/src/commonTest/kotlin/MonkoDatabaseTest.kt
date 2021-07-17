package dev.petuska.monko.core

import dev.petuska.monko.core.test.MONGO_URL
import local.test.BlockingTest
import kotlin.test.Test
import kotlin.test.assertContains

class MonkoDatabaseTest : BlockingTest {
  private lateinit var client: MonkoClient
  private lateinit var database: MonkoDatabase

  override suspend fun beforeEach() {
    client = MonkoClient(MONGO_URL)
    database = client.database("${this::class.simpleName}")
  }

  override suspend fun afterEach() {
    database.close()
    client.close()
  }

  @Test
  fun collection() = blockingTest {
    database.collection("test-collection")
  }

  @Test
  fun runCommand() = blockingTest {
    val collection = "empty-collection"
    val result = database.runCommand(
      """{ "collStats": "$collection" }"""
    ).toJson()
    assertContains(result, """"${database.name}.$collection"""")
  }
}
