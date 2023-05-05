package com.companimal.domain.member.port

import com.companimal.domain.member.dto.Member

interface GetMemberByEmailUseCase {

    fun get(email: String): Member
}
