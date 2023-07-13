package com.companimal.member.infrastructure.persistence

import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.domain.persistence.MemberReader
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberJpaReader(
    private val memberRepository: MemberRepository
): MemberReader {
    override fun findById(id: Long): Member =
        ( memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException() )
            .toMember()

    override fun findByEmail(email: String): Member =
        ( memberRepository.findByEmail(email) ?: throw NoSuchMemberException() )
            .toMember()

}