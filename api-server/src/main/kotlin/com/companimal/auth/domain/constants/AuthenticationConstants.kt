package com.companimal.auth.domain.constants

enum class AuthenticationConstants(val value: String) {
    TOKEN_CLAIM_USER_ID("uid"),
    TOKEN_CLAIM_EMAIL("email"),
    TOKEN_CLAIM_ROLE("role")
}