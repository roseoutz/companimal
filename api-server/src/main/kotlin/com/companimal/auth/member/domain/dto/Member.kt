package com.companimal.auth.member.domain.dto

import com.companimal.auth.member.domain.constants.MemberStatus
import com.companimal.auth.member.domain.constants.AuthenticationProvider
import java.time.LocalDateTime

data class Member(
    val email: String,
    val confirm: Boolean = false,
    val password: String? = null,
    val salt: String? = null,
    val id: Long? = null,
    val authenticationProvider: AuthenticationProvider? = AuthenticationProvider.NONE,
    val status: MemberStatus? = MemberStatus.IN_ACTIVE,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)