package com.companimal.domain.member.usecase

import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.persistence.MemberReader
import org.springframework.stereotype.Service


@Service
class GetMemberUseCaseImpl(
    private val memberReader: MemberReader,
): GetMemberUseCase {
    override fun get(getMemberRequest: GetMemberRequest): Member {
        return memberReader.findById(getMemberRequest.id)
    }
}