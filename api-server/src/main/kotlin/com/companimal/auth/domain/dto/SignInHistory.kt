package com.companimal.auth.domain.dto

import java.time.LocalDateTime

data class SignInHistory(
    val id: Long,
    val sessionId: String,
    val memberId: String,
    val tokenPublishHistoryList: List<TokenPublishHistory>,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)