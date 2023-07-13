package com.companimal.member.domain.adapter

import com.companimal.common.domain.validation.EmailValidator
import com.companimal.common.domain.validation.PasswordValidator
import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.exception.AlreadyRegisteredEmailException
import com.companimal.member.domain.exception.InvalidFormatEmailException
import com.companimal.member.domain.exception.InvalidFormatPasswordException
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.member.domain.persistence.MemberWriter
import com.companimal.member.domain.port.CreateMemberPort
import com.companimal.member.domain.port.CreateMemberRequest
import org.springframework.stereotype.Service

@Service
class CreateMemberAdapter(
    private val memberWriter: MemberWriter,
    private val memberReader: MemberReader,
    private val hashEncoderPort: HashEncoderPort,
    ): CreateMemberPort {
    override fun signUp(createMemberRequest: CreateMemberRequest) {
        checkRegisteredEmail(createMemberRequest)
        checkPasswordFormat(createMemberRequest)

        val salt = hashEncoderPort.getSaltValue()
        val encoded = hashEncoderPort.encode(createMemberRequest.password, salt)
        memberWriter.addMember(
            Member(
                email = createMemberRequest.email,
                password = encoded,
                salt = salt,
            )
        )
    }

    private fun checkPasswordFormat(createMemberRequest: CreateMemberRequest) {
        if (!PasswordValidator.validate(createMemberRequest.password)) {
            throw InvalidFormatPasswordException()
        }
    }

    private fun checkRegisteredEmail(createMemberRequest: CreateMemberRequest) {
        if (!EmailValidator.validate(createMemberRequest.email)) {
            throw InvalidFormatEmailException()
        }

        memberReader.findByEmail(createMemberRequest.email)?.run { throw AlreadyRegisteredEmailException() }
    }
}