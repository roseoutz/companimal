package com.companimal.member.domain.port

import com.companimal.member.domain.dto.Member

interface GetMemberByEmailUseCasePort {

    fun get(email: String): Member
}
