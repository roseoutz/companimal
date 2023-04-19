package com.companimal.domain.member.models

import java.time.LocalDateTime

data class Member(
    val id: Long?,
    val email: String,
    val password: String,
    val confirm: Boolean,
    val createdDatetime: LocalDateTime?,
    val updatedDatetime: LocalDateTime?
)