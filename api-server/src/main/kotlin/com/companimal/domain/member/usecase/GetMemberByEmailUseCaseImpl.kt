package com.companimal.domain.member.usecase

import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.persistence.MemberReader
import org.springframework.stereotype.Service

@Service
class GetMemberByEmailUseCaseImpl(
    private val memberReader: MemberReader,
): GetMemberByEmailUseCase {
    override fun get(getMemberByEmailRequest: GetMemberByEmailRequest): Member {
        return memberReader.findByEmail(getMemberByEmailRequest.email)
    }
}