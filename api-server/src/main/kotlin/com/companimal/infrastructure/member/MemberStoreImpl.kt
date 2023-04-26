package com.companimal.infrastructure.member

import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.persistence.MemberStore
import com.companimal.infrastructure.member.persistence.MemberEntity
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberStoreImpl(
    private val memberRepository: MemberRepository
): MemberStore {


    override fun addMember(member: Member) {
        val entity = MemberEntity.of(member)
        memberRepository.save(entity)
    }

    override fun updatePassword(email: String, password: String) {
        val entity = memberRepository.findByEmail(email) ?: throw NoSuchMemberException()
        entity.password = password
    }

    override fun deleteMember(id: Long) {
        val entity = memberRepository.findByIdOrNull(id) ?: throw NoSuchMemberException()
        entity.deleteMember()
    }
}