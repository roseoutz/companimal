package com.companimal.member.domain.adapter

import com.companimal.member.domain.persistence.MemberWriter
import com.companimal.member.domain.port.DeleteMemberUseCasePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteMemberUseCasePortAdapter(
    private val memberWriter: MemberWriter,
): DeleteMemberUseCasePort {

    @Transactional
    override fun delete(id: Long) {
        memberWriter.deleteMember(id)
    }
}