package com.companimal.member.domain.adapter

import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.AlreadyRegisteredEmailException
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.persistence.MemberWriter
import com.companimal.member.domain.port.CreateMemberPort
import com.companimal.member.domain.port.CreateMemberRequest
import org.springframework.stereotype.Service

@Service
class CreateMemberAdapter(
    private val memberWriter: MemberWriter,
    private val memberReader: MemberReader,
): CreateMemberPort {
    override fun signUp(createMemberRequest: CreateMemberRequest) {
        if (memberReader.findByEmail(createMemberRequest.email) != null) {
            throw AlreadyRegisteredEmailException()
        }

        memberWriter.addMember(
            Member(
                email = createMemberRequest.email,
                password = createMemberRequest.password,
                salt = createMemberRequest.salt,
            )
        )
    }

}