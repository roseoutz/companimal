package com.companimal.domain.member.adapter

import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.persistence.MemberReader
import com.companimal.domain.member.port.GetMemberUseCase
import org.springframework.stereotype.Service


@Service
class GetMemberUseCaseImpl(
    private val memberReader: MemberReader,
): GetMemberUseCase {
    override fun get(id: Long): Member {
        return memberReader.findById(id) ?: throw NoSuchMemberException()
    }
}