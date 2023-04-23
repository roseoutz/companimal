package com.companimal.application.member.port.`in`

import com.companimal.domain.member.Member

interface MemberPort {

    fun getMember(id: Long): Member

    fun getMemberByEmail(email: String): Member

    fun signUp(member: Member)

    fun deleteMember(id: Long)

    fun updatePassword(email: String, password: String)

}