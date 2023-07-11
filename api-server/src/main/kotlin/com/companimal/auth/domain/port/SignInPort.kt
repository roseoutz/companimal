package com.companimal.auth.domain.port

interface SignInPort {
    fun signIn(signInRequest: SignInRequest);
}

data class SignInRequest(
    val email: String,
    val password: String,
)