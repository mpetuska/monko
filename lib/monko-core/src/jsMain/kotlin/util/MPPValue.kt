package dev.petuska.monko.core.util

public actual operator fun <T : Any> MPPValue<T>.invoke(): T = js ?: default
