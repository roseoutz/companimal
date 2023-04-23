package com.companimal.domain.member.models

import com.companimal.domain.member.constants.MemberStatus
import java.time.LocalDateTime

data class Member(
    val id: Long? = null,
    val email: String,
    val password: String,
    val confirm: Boolean,
    val status: MemberStatus,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)