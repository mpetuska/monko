package dev.petuska.monko.core

import local.test.BlockingTest
import kotlin.test.Test
import kotlin.test.assertContains

class MonkoDatabaseTest : BlockingTest {
  private lateinit var client: MonkoClient
  private lateinit var database: MonkoDatabase

  override suspend fun beforeEach() {
    client = MonkoClient("mongodb://localhost:27017")
    database = client.database("${this::class.simpleName}")
  }

  override suspend fun afterEach() {
    client.close()
  }

  @Test
  fun collection() = test {
    database.collection("test-collection")
  }

  @Test
  fun runCommand() = test {
    val collection = "empty-collection"
    val result = database.runCommand(
      """{ "collStats": "$collection" }"""
    ).toJson()
    assertContains(result, """"${database.name}.$collection"""")
  }
}
