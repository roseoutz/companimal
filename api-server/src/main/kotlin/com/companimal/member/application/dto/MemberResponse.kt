package com.companimal.auth.member.application.dto

import com.companimal.auth.member.domain.constants.MemberStatus
import com.companimal.auth.member.domain.dto.Member
import java.time.LocalDateTime

data class MemberResponse(
    val id: Long?,
    val email: String,
    val confirm: Boolean,
    val status: MemberStatus?,
    val createdDatetime: LocalDateTime?,
    val updatedDatetime: LocalDateTime?,
) {
    companion object {
        fun of(member: Member): MemberResponse = MemberResponse(
            id = member.id,
            email = member.email,
            confirm = member.confirm,
            status = member.status,
            createdDatetime = member.createdDatetime,
            updatedDatetime = member.updatedDatetime,
        )
    }
}
