package com.companimal.auth.domain.dto

import com.companimal.auth.domain.constants.SignInSourceType
import java.time.LocalDateTime

data class SignInHistory(
    val id: Long? = null,
    val sessionId: String? = null,
    val memberId: Long? = null,
    val signInSourceType: SignInSourceType,
    val isSuccess: Boolean,
    val failReason: String? = null,
    val tokenPublishHistoryList: List<TokenPublishHistory> = listOf(),
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)