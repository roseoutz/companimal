package com.companimal.auth.member.domain.port

import com.companimal.auth.member.domain.dto.Member

interface GetMemberByEmailUseCasePort {

    fun get(email: String): Member
}
