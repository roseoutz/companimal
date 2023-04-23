package com.companimal.application.member.port.out

import com.companimal.domain.member.Member

interface MemberPersistencePort {

    fun findById(id: Long): Member

    fun findByEmail(email: String): Member

    fun addMember(member: Member)

    fun updatePassword(email: String, password:String)

    fun deleteMember(id: Long)

}