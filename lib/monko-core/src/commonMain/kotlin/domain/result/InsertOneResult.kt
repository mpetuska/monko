package dev.petuska.monko.core.domain.result

public data class InsertOneResult(
  val acknowledged: Boolean,
  val insertedId: String?
)
