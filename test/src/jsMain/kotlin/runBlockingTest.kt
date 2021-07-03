package local.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.promise
import kotlin.coroutines.EmptyCoroutineContext

actual typealias CoroutineScope = kotlinx.coroutines.CoroutineScope

actual fun runBlockingTest(test: suspend CoroutineScope.() -> Unit): dynamic {
  val scope = CoroutineScope(EmptyCoroutineContext)
  return scope.promise(block = test).then {
    scope.cancel()
  }.catch {
    scope.cancel()
    throw it
  }
}
