package com.companimal.member.domain.adapter

import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.port.CheckExistMemberPort
import org.springframework.stereotype.Service

@Service
class CheckExistMemberAdapter(
    private val memberReader: MemberReader
): CheckExistMemberPort {

    override fun isExistMember(email: String): Boolean {
        return memberReader.findByEmail(email) != null
    }

    override fun isExistMember(id: Long): Boolean {
        return memberReader.findById(id) != null
    }
}