package com.companimal.member.domain.port

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank


interface CreateMemberPort {
    fun signUp(createMemberRequest: CreateMemberRequest)
}

@Schema(description = "사용자 회원가입 요청")
data class CreateMemberRequest(
    @field:Schema(description = "회원가입을 할 Email", required = true)
    @field:NotBlank
    val email: String,

    @field:Schema(description = "비밀번호", required = true)
    @field:NotBlank
    val password: String,

    @field:Schema(description = "비밀번호 솔트", required = true)
    @field:NotBlank
    val salt: String,
)