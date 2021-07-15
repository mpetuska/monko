package dev.petuska.monko.core

import dev.petuska.monko.core.test.MONGO_URL
import local.test.BlockingTest
import kotlin.test.Test

class MonkoClientTest : BlockingTest {
  lateinit var client: MonkoClient

  override suspend fun beforeEach() {
    client = MonkoClient(MONGO_URL)
  }

  override suspend fun afterEach() {
    client.close()
  }

  @Test
  fun database() = blockingTest {
    client.database("test-database")
  }
}
