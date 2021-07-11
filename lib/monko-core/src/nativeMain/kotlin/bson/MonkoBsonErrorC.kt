package dev.petuska.monko.core.bson

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.get
import kotlinx.cinterop.toKStringFromUtf8
import mongoc.bson_error_t

public class MonkoBsonErrorC(public val c: CPointer<bson_error_t>) {
  public val domain: UInt by lazy { c[0].domain }
  public val code: UInt by lazy { c[0].code }
  public val message: String by lazy { c[0].message.toKStringFromUtf8() }

  public fun toJson(): String = buildString {
    append("""{ "domain": $domain,""")
    append(""" "code": $code,""")
    append(""" "message": "$message" }""")
  }
}

public fun CPointer<bson_error_t>.extractJson(): String = MonkoBsonErrorC(this).toJson()
