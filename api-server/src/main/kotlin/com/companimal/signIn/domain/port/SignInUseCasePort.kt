package com.companimal.signIn.domain.port

interface SignInUseCasePort {
    fun signIn(signInRequest: SignInRequest);
}

data class SignInRequest(
    val email: String,
    val password: String,
)