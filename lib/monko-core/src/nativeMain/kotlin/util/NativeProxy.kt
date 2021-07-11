package dev.petuska.monko.core.util

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer

public interface NativeProxy<C : CPointed> {
  public val c: CPointer<C>
}
