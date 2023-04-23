package com.companimal.domain.member.port.`in`

import com.companimal.domain.member.models.Member

interface MemberServicePort {

    fun getMember(id: Long): Member

    fun getMemberByEmail(email: String): Member

    fun signUp(member: Member)

    fun deleteMember(id: Long)

    fun updatePassword(email: String, password: String)

}