package local.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun runBlockingTest(test: suspend CoroutineScope.() -> Unit) =
  runBlocking(block = test)
