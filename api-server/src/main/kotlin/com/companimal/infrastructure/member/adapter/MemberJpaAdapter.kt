package com.companimal.infrastructure.member.adapter

import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.models.Member
import com.companimal.domain.member.port.MemberPersistencePort
import com.companimal.infrastructure.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

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

    override fun addMember(member: Member): Member {
        TODO("Not yet implemented")
    }
}