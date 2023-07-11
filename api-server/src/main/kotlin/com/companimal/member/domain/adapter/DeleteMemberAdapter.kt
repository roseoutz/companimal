package com.companimal.member.domain.adapter

import com.companimal.member.domain.persistence.MemberWriter
import com.companimal.member.domain.port.DeleteMemberPort
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