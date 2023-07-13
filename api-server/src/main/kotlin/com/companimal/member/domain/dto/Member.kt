package com.companimal.member.domain.dto

import com.companimal.member.domain.constants.MemberStatus
import java.time.LocalDateTime

data class Member(
    val id: Long? = null,
    val email: String,
    val password: String? = null,
    val salt: String? = null,
    val isConfirmed: Boolean = false,
    val status: MemberStatus? = MemberStatus.IN_ACTIVE,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)