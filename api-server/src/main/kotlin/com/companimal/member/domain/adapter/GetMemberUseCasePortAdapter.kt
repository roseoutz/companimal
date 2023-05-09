package com.companimal.member.domain.adapter

import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.port.GetMemberUseCasePort
import org.springframework.stereotype.Service


@Service
class GetMemberUseCasePortAdapter(
    private val memberReader: MemberReader,
): GetMemberUseCasePort {
    override fun get(id: Long): Member {
        return memberReader.findById(id) ?: throw NoSuchMemberException()
    }
}