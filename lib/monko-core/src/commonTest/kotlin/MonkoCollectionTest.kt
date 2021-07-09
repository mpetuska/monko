package dev.petuska.monko.core

import local.test.runBlockingTest
import kotlin.test.Test

class MonkoCollectionTest {
  @Test
  fun isAbleToInitialise() = runBlockingTest {
    val client = MonkoClient("mongodb://localhost:27017")
    val db = client.database("test-db")
    val col = db.collection("test-collection")
    col.close()
    db.close()
    client.close()
  }
}
