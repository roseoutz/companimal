package com.companimal.member.domain.port

import com.companimal.member.domain.dto.Member

interface GetMemberByEmailPort {

    fun get(email: String): Member
}
