package com.companimal.domain.member.port

import com.companimal.domain.member.dto.Member

interface GetMemberByEmailUseCase {

    fun get(getMemberByEmailRequest: GetMemberByEmailRequest): Member
}

data class GetMemberByEmailRequest(
    val email: String,
)