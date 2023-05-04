package com.companimal.domain.member.port

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank

interface SignUpUseCase {
    fun signUp(signUpRequest: SignUpRequest)
}

data class SignUpRequest(
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String,
)