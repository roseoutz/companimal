package com.companimal.auth.domain.port

import com.companimal.auth.domain.constants.SignInSourceType

interface SignInPort {
    fun signIn(signInRequest: SignInRequest): SignInResponse
}

data class SignInRequest(
    val email: String,
    val password: String,
    val signInSourceType: SignInSourceType? = SignInSourceType.UNKNOWN,
    val sessionId: String? = null,
)

data class SignInResponse(
    val sessionId: String,
    val token: String
)