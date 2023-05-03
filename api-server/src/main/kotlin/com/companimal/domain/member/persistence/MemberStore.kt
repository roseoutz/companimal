package com.companimal.domain.member.persistence

import com.companimal.domain.member.dto.Member

interface MemberStore {

    fun addMember(member: Member)

    fun updatePassword(id: Long, password:String)

    fun deleteMember(id: Long)

}