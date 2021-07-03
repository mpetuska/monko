package dev.petuska.monko.core

import local.test.runBlockingTest
import kotlin.test.Test

class MonkoCollectionTest {
  @Test
  fun isAbleToInitialise() = runBlockingTest {
    MonkoClient("mongodb://localhost:27017")
      .database("test-db")
      .collection("test-collection")
  }
}
