import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.api.provider.Property
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

typealias Lambda<R, V> = R.() -> V

fun <R, V> Lambda<R, V>.toClosure(owner: Any? = null, thisObj: Any? = null) = object : Closure<V>(owner, thisObj) {
  @Suppress("UNCHECKED_CAST")
  fun doCall() {
    with(delegate as R) {
      this@toClosure()
    }
  }
}

fun <R, V> closureOf(owner: Any? = null, thisObj: Any? = null, func: R.() -> V) = func.toClosure(owner, thisObj)

infix fun <T> Property<T>.by(value: T) {
  set(value)
}

object Git {
  val headCommitHash by lazy {
    val child = Runtime.getRuntime().exec("git rev-parse --verify HEAD")
    child.waitFor()
    child.inputStream.readAllBytes().toString(Charset.defaultCharset()).trim()
  }
}

fun Project.execAndCapture(cmd: String): String? =
    ByteArrayOutputStream().use { stream ->
      try {
        exec {
          commandLine(*cmd.split(" ").toTypedArray())
          standardOutput = stream
        }
          .takeIf { it.exitValue == 0 }
          .let { stream.toString().trim() }
      } catch (e: Exception) {
        null
      }
    }
