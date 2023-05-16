package com.companimal.auth.member.domain.adapter

import com.companimal.auth.member.domain.dto.Member
import com.companimal.auth.member.domain.exception.NoSuchMemberException
import com.companimal.auth.member.domain.persistence.MemberReader
import com.companimal.auth.member.domain.port.GetMemberByEmailUseCasePort
import org.springframework.stereotype.Service

@Service
class GetMemberByEmailUseCasePortAdapter(
    private val memberReader: MemberReader,
): GetMemberByEmailUseCasePort {
    override fun get(email: String): Member {
        return memberReader.findByEmail(email) ?: throw NoSuchMemberException()
    }
}