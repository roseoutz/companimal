package com.companimal.member.domain.persistence

import com.companimal.member.domain.dto.Member

interface MemberWriter {

    fun addMember(member: Member)

    fun updatePassword(id: Long, password:String, salt: String)

    fun deleteMember(id: Long)

}