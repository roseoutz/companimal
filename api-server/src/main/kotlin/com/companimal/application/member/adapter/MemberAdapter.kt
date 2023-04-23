package com.companimal.application.member.adapter

import com.companimal.domain.member.models.Member
import com.companimal.domain.member.port.`in`.MemberServicePort
import org.springframework.stereotype.Service

@Service
class MemberAdapter(
    private val memberServicePort: MemberServicePort
) {

    fun getMember(id: Long): Member {
        return memberServicePort.getMember(id)
    }

    fun getMemberByEmail(email: String): Member {
        return memberServicePort.getMemberByEmail(email)
    }

    fun signUp(member: Member) {
        memberServicePort.signUp(member)
    }

    fun deleteMember(id: Long) {
        memberServicePort.deleteMember(id)
    }

    fun updatePassword(email: String, password: String) {
        memberServicePort.updatePassword(email, password)
    }
}