package com.companimal.application.member.adapter

import com.companimal.domain.member.Member
import com.companimal.application.member.port.`in`.MemberPort
import com.companimal.application.member.port.out.MemberPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAdapter(
    private val memberPersistencePort: MemberPersistencePort
): MemberPort {

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