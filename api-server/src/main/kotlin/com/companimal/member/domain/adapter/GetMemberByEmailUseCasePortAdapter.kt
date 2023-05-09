package com.companimal.member.domain.adapter

import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.port.GetMemberByEmailUseCasePort
import org.springframework.stereotype.Service

@Service
class GetMemberByEmailUseCasePortAdapter(
    private val memberReader: MemberReader,
): GetMemberByEmailUseCasePort {
    override fun get(email: String): Member {
        return memberReader.findByEmail(email) ?: throw NoSuchMemberException()
    }
}