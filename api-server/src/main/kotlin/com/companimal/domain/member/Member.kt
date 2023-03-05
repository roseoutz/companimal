package com.companimal.domain.member

import java.time.LocalDateTime

data class Member(
    val id: Long?,
    val email: String,
    val password: String,
    val confirm: Boolean,
    val createdDatetime: LocalDateTime,
    val updatedDatetime: LocalDateTime
)