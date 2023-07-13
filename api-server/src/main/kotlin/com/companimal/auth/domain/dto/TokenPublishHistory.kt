package com.companimal.auth.domain.dto

import java.time.LocalDateTime

data class TokenPublishHistory(
    val jwtId: String,
    val publishDateTime: LocalDateTime,
    val expiredDateTime: LocalDateTime,
    val isExpired: Boolean,
    val id: Long? = null,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)