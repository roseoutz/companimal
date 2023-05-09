package com.companimal.member.domain.port

import com.companimal.member.domain.dto.Member

interface GetMemberUseCasePort {

    fun get(id: Long): Member
}
