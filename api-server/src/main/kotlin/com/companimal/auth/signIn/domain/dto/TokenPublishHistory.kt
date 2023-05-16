package com.companimal.auth.signIn.domain.dto

import com.companimal.auth.signIn.domain.constants.SignInSourceType
import java.time.LocalDateTime

data class TokenPublishHistory(
    val id: String,
    val sessionId: String,
    val signInSourceType: com.companimal.auth.signIn.domain.constants.SignInSourceType,
    val publishDateTime: LocalDateTime,
    val expiredDateTime: LocalDateTime,
    val isExpired: Boolean,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)