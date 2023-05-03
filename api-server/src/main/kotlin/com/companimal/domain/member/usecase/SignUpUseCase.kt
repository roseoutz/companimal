package com.companimal.domain.member.usecase

interface SignUpUseCase {
    fun signUp(signUpRequest: SignUpRequest)
}

data class SignUpRequest(
    val email: String,
    val password: String,
)