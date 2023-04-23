package com.companimal.infrastructure.member.adapter

import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.models.Member
import com.companimal.domain.member.port.out.MemberPersistencePort
import com.companimal.infrastructure.member.persistence.MemberEntity
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberJpaAdapter(
    private val memberRepository: MemberRepository
): MemberPersistencePort {
    override fun findById(id: Long): Member {
        val entity = memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException()
        return entity.toMember()
    }

    override fun findByEmail(email: String): Member {
        val entity = memberRepository.findByEmail(email) ?: throw NoSuchMemberException()
        return entity.toMember()
    }

    @Transactional(readOnly = true)
    override fun addMember(member: Member) {
        val entity = MemberEntity.of(member)
        memberRepository.save(entity)
    }

    @Transactional(readOnly = true)
    override fun updatePassword(id: Long, password: String) {
        val entity = memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException()
        entity.password = password
    }

    @Transactional(readOnly = true)
    override fun deleteMember(id: Long) {
        memberRepository.deleteById(id)
    }
}