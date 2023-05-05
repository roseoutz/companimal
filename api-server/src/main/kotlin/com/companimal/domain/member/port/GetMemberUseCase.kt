package com.companimal.domain.member.port

import com.companimal.domain.member.dto.Member

interface GetMemberUseCase {

    fun get(id: Long): Member
}
