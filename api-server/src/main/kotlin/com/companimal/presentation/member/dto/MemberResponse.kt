package com.companimal.presentation.member.dto

import com.companimal.domain.member.constants.MemberStatus
import com.companimal.domain.member.models.Member
import java.time.LocalDateTime

data class MemberResponse(
    val id: Long?,
    val email: String,
    val confirm: Boolean,
    val status: MemberStatus,
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