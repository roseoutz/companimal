package com.companimal.member.domain.dto

import com.companimal.member.domain.constants.MemberStatus
import java.time.LocalDateTime

data class Member(
    val email: String,
    val password: String,
    val confirm: Boolean = false,
    val salt: String? = null,
    val id: Long? = null,
    val status: MemberStatus? = MemberStatus.IN_ACTIVE,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)