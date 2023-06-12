package com.companimal.auth.member.domain.adapter

import com.companimal.auth.member.domain.persistence.MemberWriter
import com.companimal.auth.member.domain.port.DeleteMemberPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteMemberAdapter(
    private val memberWriter: MemberWriter,
): DeleteMemberPort {

    @Transactional
    override fun delete(id: Long) {
        memberWriter.deleteMember(id)
    }
}