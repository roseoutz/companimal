package com.companimal.member.domain.adapter

import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.persistence.MemberWriter
import com.companimal.member.domain.port.DeleteMemberPort
import com.companimal.member.domain.exception.NotRegisteredMemberException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteMemberAdapter(
    private val memberReader: MemberReader,
    private val memberWriter: MemberWriter,
): DeleteMemberPort {

    @Transactional
    override fun delete(id: Long) {
        checkExistMember(id)

        memberWriter.deleteMember(id)
    }

    private fun checkExistMember(id: Long) {
        if (memberReader.findById(id) == null) {
            throw NotRegisteredMemberException()
        }
    }
}