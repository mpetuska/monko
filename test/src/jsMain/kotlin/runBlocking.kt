package local.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.promise
import kotlin.coroutines.EmptyCoroutineContext

internal actual fun runBlocking(test: suspend CoroutineScope.() -> Unit): dynamic =
  CoroutineScope(EmptyCoroutineContext).promise(block = test)
