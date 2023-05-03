package com.companimal.presentation.member.dto

data class PasswordChangeRequest(
    val id: Long,
    val oldPassword: String,
    val newPassword: String,
)