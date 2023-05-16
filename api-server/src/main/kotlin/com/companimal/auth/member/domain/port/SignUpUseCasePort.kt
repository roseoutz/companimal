package com.companimal.auth.member.domain.port

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank


interface SignUpUseCasePort {
    fun signUp(signUpRequest: SignUpRequest)
}

@Schema(description = "사용자 회원가입 요청")
data class SignUpRequest(
    @field:Schema(description = "회원가입을 할 Email", required = true)
    @field:NotBlank
    val email: String,

    @field:Schema(description = "비밀번호", required = true)
    @field:NotBlank
    val password: String,
)