package com.companimal.auth.member.domain.port

import com.companimal.auth.member.domain.dto.Member

interface GetMemberUseCasePort {

    fun get(id: Long): Member
}
