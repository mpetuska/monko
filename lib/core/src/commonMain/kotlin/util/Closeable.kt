package dev.petuska.monko.core.util

public expect interface Closeable {
  public fun close()
}

public interface JvmCloseable : Closeable
public interface JsCloseable : Closeable
public interface NativeCloseable : Closeable
