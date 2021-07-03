package dev.petuska.monko.core

import local.test.runBlockingTest
import kotlin.test.Test

class MonkoDatabaseTest {
  @Test
  fun isAbleToInitialise() = runBlockingTest {
    MonkoClient("mongodb://localhost:27017")
      .database("test-db")
  }
}
