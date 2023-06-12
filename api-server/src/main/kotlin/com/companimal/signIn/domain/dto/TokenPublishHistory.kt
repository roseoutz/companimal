package com.companimal.signIn.domain.dto

import com.companimal.signIn.domain.constants.SignInSourceType
import java.time.LocalDateTime

data class TokenPublishHistory(
    val sessionId: String,
    val signInSourceType: SignInSourceType,
    val publishDateTime: LocalDateTime,
    val expiredDateTime: LocalDateTime,
    val isExpired: Boolean,
    val id: Long?,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)