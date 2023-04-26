package com.companimal.domain.member.service

import com.companimal.domain.member.command.MemberCommand
import com.companimal.domain.member.dto.Member

interface MemberService {

    fun getMember(id: Long): Member

    fun getMemberByEmail(email: String): Member

    fun createMember(memberCreateCommand: MemberCommand.Companion.MemberCreateCommand)

    fun deleteMember(id: Long)

    fun updatePassword(email: String, password: String)

}