package com.companimal.member.domain.adapter

import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.port.GetMemberByEmailPort
import org.springframework.stereotype.Service

@Service
class GetMemberByEmailAdapter(
    private val memberReader: MemberReader,
): GetMemberByEmailPort {
    override fun get(email: String): Member {
        return memberReader.findByEmail(email) ?: throw NoSuchMemberException()
    }
}