package com.companimal.member.domain.adapter

import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.port.GetMemberPort
import org.springframework.stereotype.Service


@Service
class GetMemberAdapter(
    private val memberReader: MemberReader,
): GetMemberPort {
    override fun get(id: Long): Member {
        return memberReader.findById(id) ?: throw NoSuchMemberException()
    }
}