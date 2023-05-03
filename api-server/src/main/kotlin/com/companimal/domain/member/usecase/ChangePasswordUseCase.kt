package com.companimal.domain.member.usecase

interface ChangePasswordUseCase {
    fun changePassword(changePasswordRequest: ChangePasswordRequest)
}

data class ChangePasswordRequest(
    val id: Long,
    val oldPassword: String,
    val newPassword: String,
)