package dev.petuska.monko.core

import local.test.runBlockingTest
import kotlin.test.Test

class MonkoClientTest {
  @Test
  fun isAbleToInitialise() = runBlockingTest {
    MonkoClient("mongodb://localhost:27017")
  }
}
