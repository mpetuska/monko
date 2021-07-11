package dev.petuska.monko.core

import local.test.BlockingTest
import kotlin.test.Test

class MonkoClientTest : BlockingTest {
  lateinit var client: MonkoClient

  override suspend fun beforeEach() {
    client = MonkoClient("mongodb://localhost:27017")
  }

  override suspend fun afterEach() {
    client.close()
  }

  @Test
  fun database() = test {
    client.database("test-database")
  }
}
