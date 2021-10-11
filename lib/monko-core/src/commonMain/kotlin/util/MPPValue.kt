package dev.petuska.monko.core.util

public expect operator fun <T : Any> MPPValue<T>.invoke(): T

public data class MPPValue<T : Any>(
  val default: T,
  val jvm: T? = null,
  val js: T? = null,
  val native: T? = null,
)
