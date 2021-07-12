package local.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

internal actual fun runBlocking(test: suspend CoroutineScope.() -> Unit) = runBlocking(block = test)
