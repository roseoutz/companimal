package com.companimal.auth.signIn.domain.dto

import java.time.LocalDateTime

data class SignInHistory(
    val id: Long,
    val sessionId: String,
    val memberId: String,
    val tokenPublishHistoryList: List<com.companimal.auth.signIn.domain.dto.TokenPublishHistory>,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)