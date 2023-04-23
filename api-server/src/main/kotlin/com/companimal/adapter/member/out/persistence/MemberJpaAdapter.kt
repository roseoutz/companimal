package com.companimal.adapter.member.out.persistence

import com.companimal.application.member.exception.NoSuchMemberException
import com.companimal.domain.member.Member
import com.companimal.application.member.port.out.MemberPersistencePort
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
    override fun updatePassword(email: String, password: String) {
        val entity = memberRepository.findByEmail(email) ?: throw NoSuchMemberException()
        entity.password = password
    }

    @Transactional(readOnly = true)
    override fun deleteMember(id: Long) {
        val entity = memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException()
        entity.deleteMember()
    }
}