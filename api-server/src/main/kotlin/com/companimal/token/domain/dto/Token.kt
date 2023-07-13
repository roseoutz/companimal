package com.companimal.token.domain.dto

import java.util.*

data class Token(
    val id: String,
    val token: String,
    val issuedAt: Date,
    val expiredAt: Date,
)
