package com.companimal.member.infrastructure.persistence

import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.domain.persistence.MemberWriter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberJpaWriter(
    private val memberRepository: MemberRepository
): MemberWriter {


    override fun addMember(member: Member) {
        val entity = MemberEntity.of(member)
        memberRepository.save(entity)
    }

    override fun updatePassword(id: Long, password: String, salt: String) =
        (memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException())
            .updatePassword(password, salt)


    override fun deleteMember(id: Long) =
        (memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException())
            .deleteMember()
}