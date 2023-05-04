package com.companimal.domain.member.adapter

import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.persistence.MemberReader
import com.companimal.domain.member.port.GetMemberByEmailRequest
import com.companimal.domain.member.port.GetMemberByEmailUseCase
import org.springframework.stereotype.Service

@Service
class GetMemberByEmailUseCaseImpl(
    private val memberReader: MemberReader,
): GetMemberByEmailUseCase {
    override fun get(getMemberByEmailRequest: GetMemberByEmailRequest): Member {
        return memberReader.findByEmail(getMemberByEmailRequest.email)
    }
}