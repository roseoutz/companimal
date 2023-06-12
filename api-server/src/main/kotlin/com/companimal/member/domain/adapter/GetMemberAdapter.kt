package com.companimal.auth.member.domain.adapter

import com.companimal.auth.member.domain.dto.Member
import com.companimal.auth.member.domain.exception.NoSuchMemberException
import com.companimal.auth.member.domain.persistence.MemberReader
import com.companimal.auth.member.domain.port.GetMemberPort
import org.springframework.stereotype.Service


@Service
class GetMemberAdapter(
    private val memberReader: MemberReader,
): GetMemberPort {
    override fun get(id: Long): Member {
        return memberReader.findById(id) ?: throw NoSuchMemberException()
    }
}