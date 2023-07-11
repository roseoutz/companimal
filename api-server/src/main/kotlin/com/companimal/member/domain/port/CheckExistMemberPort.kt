package com.companimal.member.domain.port

interface CheckExistMemberPort {

    fun isExistMember(email: String): Boolean

    fun isExistMember(id: Long): Boolean
}