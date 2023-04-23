package com.companimal.domain.member.port.out

import com.companimal.domain.member.models.Member

interface MemberPersistencePort {

    fun findById(id: Long): Member

    fun findByEmail(email: String): Member

    fun addMember(member: Member)

    fun updatePassword(email: String, password:String)

    fun deleteMember(id: Long)

}