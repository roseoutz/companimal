package com.companimal.domain.member.port

import com.companimal.domain.member.models.Member

interface MemberService {

    fun findById(id: Long): Member
    fun findByEmail(email: String): Member

    fun addMember(member: Member): Member

}