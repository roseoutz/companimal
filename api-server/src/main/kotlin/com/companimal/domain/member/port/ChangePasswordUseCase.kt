package com.companimal.domain.member.port

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank

interface ChangePasswordUseCase {
    fun changePassword(changePasswordRequest: ChangePasswordRequest)
}

data class ChangePasswordRequest(
    @field:NotBlank
    val id: Long,
    @field:NotBlank
    val oldPassword: String,
    @field:NotBlank
    val newPassword: String,
)