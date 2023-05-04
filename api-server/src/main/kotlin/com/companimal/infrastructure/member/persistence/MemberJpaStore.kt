package com.companimal.infrastructure.member.persistence

import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.persistence.MemberStore
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberJpaStore(
    private val memberRepository: MemberRepository
): MemberStore {


    override fun addMember(member: Member) {
        val entity = MemberEntity.of(member)
        memberRepository.save(entity)
    }

    override fun updatePassword(id: Long, password: String, salt: String) {
        val entity = memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException()
        entity.password = password
        entity.salt = salt
    }

    override fun deleteMember(id: Long) {
        val entity = memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException()
        entity.deleteMember()
    }
}