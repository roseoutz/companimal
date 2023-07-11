package com.companimal.auth.domain.dto

import java.time.LocalDateTime

data class SignInHistory(
    val sessionId: String,
    val memberId: Long,
    val tokenPublishHistoryList: List<TokenPublishHistory> = listOf(),
    val id: Long? = null,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)