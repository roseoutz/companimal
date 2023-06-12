package com.companimal.auth.member.domain.port

import com.companimal.auth.member.domain.dto.Member

interface GetMemberByEmailPort {

    fun get(email: String): Member
}
