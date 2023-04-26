package com.companimal.infrastructure.member

import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.persistence.MemberReader
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberReaderImpl(
    private val memberRepository: MemberRepository
): MemberReader {
    override fun findById(id: Long): Member {
        val entity = memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException()
        return entity.toMember()
    }

    override fun findByEmail(email: String): Member {
        val entity = memberRepository.findByEmail(email) ?: throw NoSuchMemberException()
        return entity.toMember()
    }
}