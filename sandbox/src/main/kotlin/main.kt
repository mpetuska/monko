package local.sandbox

import dev.petuska.monko.core.CoreLib
import kotlinx.coroutines.runBlocking

fun main() {
  val core = CoreLib()
  println(core.sampleApi().withPlatform())
  runBlocking {
    suspendingMain()
  }
}

suspend fun suspendingMain() {
  val core = CoreLib()
  println(core.sampleSuspendApi().withPlatformSuspend())
}
