package com.companimal.domain.member.dto

import com.companimal.domain.member.constants.MemberStatus
import java.time.LocalDateTime

data class Member(
    val email: String,
    val confirm: Boolean,
    val status: MemberStatus,
    val password: String,
    val salt: String? = null,
    val id: Long? = null,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)