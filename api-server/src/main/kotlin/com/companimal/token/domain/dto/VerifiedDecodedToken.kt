package com.companimal.token.domain.dto

data class VerifiedDecodedToken(
    val id: String,
    val payloads: Map<String, Any>,
)
