package local.test

import kotlinx.coroutines.CoroutineScope

internal expect fun runBlocking(test: suspend CoroutineScope.() -> Unit)

interface BlockingTest {
  suspend fun beforeEach() {}
  suspend fun afterEach() {}

  fun blockingTest(action: suspend CoroutineScope.() -> Unit) = runBlocking {
    try {
      beforeEach()
      action()
    } finally {
      afterEach()
    }
  }
}
