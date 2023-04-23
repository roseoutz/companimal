package com.companimal.domain.member.adapter

import com.companimal.domain.member.models.Member
import com.companimal.domain.member.port.`in`.MemberServicePort
import com.companimal.domain.member.port.out.MemberPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServicePortAdapter(
    private val memberPersistencePort: MemberPersistencePort
): MemberServicePort {

    override fun getMember(id: Long): Member {
        return memberPersistencePort.findById(id)
    }

    override fun getMemberByEmail(email: String): Member {
        return memberPersistencePort.findByEmail(email)
    }

    @Transactional
    override fun signUp(member: Member) {
        memberPersistencePort.addMember(member)
    }

    @Transactional
    override fun deleteMember(id: Long) {
        memberPersistencePort.deleteMember(id)
    }

    @Transactional
    override fun updatePassword(email: String, password: String) {
        memberPersistencePort.updatePassword(email, password)
    }

}