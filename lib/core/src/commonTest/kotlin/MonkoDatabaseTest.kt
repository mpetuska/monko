package dev.petuska.monko.core

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import local.test.runBlockingTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContains

class MonkoDatabaseTest {
  private lateinit var client: Deferred<MonkoClient>

  @BeforeTest
  fun setUp() = runBlockingTest {
    client = async { MonkoClient("mongodb://localhost:27017") }
  }

  @AfterTest
  fun tearDown() = runBlockingTest {
    client.await().close()
  }

  @Test
  fun isAbleToInitialise() = runBlockingTest {
    val client = client.await()
    client.database("test-db").close()
  }

  @Test
  fun runTestCommand() = runBlockingTest {
    val client = client.await()
    val db = client.database("test-db")
    val collection = "empty-collection"
    val result = db.runCommand(
      """{
        |  "collStats": "$collection"
        |}""".trimMargin()
    ).toJson()

    db.close()

    println(result)
    assertContains(result, """"test-db.$collection"""")
  }
}
