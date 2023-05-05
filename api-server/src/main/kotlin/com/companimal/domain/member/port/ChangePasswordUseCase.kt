package com.companimal.domain.member.port

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive


interface ChangePasswordUseCase {
    fun changePassword(changePasswordRequest: ChangePasswordRequest)
}


@Schema(description = "비밀번호 변경 요청")
data class ChangePasswordRequest(
    @field:Schema(description = "비밀번호 변경 요청할 사용자의 ID 값", required = true)
    @field:Positive
    val id: Long,

    @field:Schema(description = "기존 비밀번호", required = true)
    @field:NotBlank
    val oldPassword: String,

    @field:Schema(description = "신규 비밀번호", required = true)
    @field:NotBlank
    val newPassword: String,
)