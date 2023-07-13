package com.companimal.member.domain.port

import com.companimal.member.domain.dto.Member

interface GetMemberPort {

    fun get(id: Long): Member
}
