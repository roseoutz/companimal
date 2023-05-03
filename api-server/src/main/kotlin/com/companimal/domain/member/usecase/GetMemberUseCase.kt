package com.companimal.domain.member.usecase

import com.companimal.domain.member.dto.Member

interface GetMemberUseCase {

    fun get(getMemberRequest: GetMemberRequest): Member
}

data class GetMemberRequest(
    val id: Long,
)