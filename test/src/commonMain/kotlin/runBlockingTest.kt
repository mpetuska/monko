package local.test

import kotlinx.coroutines.CoroutineScope

expect fun runBlockingTest(test: suspend CoroutineScope.() -> Unit)
