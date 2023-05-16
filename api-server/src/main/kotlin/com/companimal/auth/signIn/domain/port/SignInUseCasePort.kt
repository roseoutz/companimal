package com.companimal.auth.signIn.domain.port

interface SignInUseCasePort {
    fun signIn(signInRequest: com.companimal.auth.signIn.domain.port.SignInRequest);
}

data class SignInRequest(
    val email: String,
    val password: String,
)