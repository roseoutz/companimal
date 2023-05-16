package com.companimal.auth.member.domain.adapter

import com.companimal.common.domain.validation.EmailValidator
import com.companimal.common.domain.validation.PasswordValidator
import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.auth.member.domain.dto.Member
import com.companimal.auth.member.domain.exception.AlreadyRegisteredEmailException
import com.companimal.auth.member.domain.exception.InvalidFormatEmailException
import com.companimal.auth.member.domain.exception.InvalidFormatPasswordException
import com.companimal.auth.member.domain.persistence.MemberReader
import com.companimal.auth.member.domain.persistence.MemberWriter
import com.companimal.auth.member.domain.port.SignUpRequest
import com.companimal.auth.member.domain.port.SignUpUseCasePort
import org.springframework.stereotype.Service

@Service
class SignUpUseCasePortAdapter(
    private val memberReader: MemberReader,
    private val memberWriter: MemberWriter,
    private val hashEncoderPort: HashEncoderPort,
): SignUpUseCasePort {
    override fun signUp(signUpRequest: SignUpRequest) {
        checkRegisteredEmail(signUpRequest)
        checkPasswordFormat(signUpRequest)

        val salt = hashEncoderPort.getSaltValue()
        val encoded = hashEncoderPort.encode(signUpRequest.password, salt)
        memberWriter.addMember(
            Member(
                email = signUpRequest.email,
                password = encoded,
                salt = salt,
            )
        )
    }

    private fun checkPasswordFormat(signUpRequest: SignUpRequest) {
        if (!PasswordValidator.validate(signUpRequest.password)) {
            throw InvalidFormatPasswordException()
        }
    }

    private fun checkRegisteredEmail(signUpRequest: SignUpRequest) {
        if (!EmailValidator.validate(signUpRequest.email)) {
            throw InvalidFormatEmailException()
        }

        memberReader.findByEmail(signUpRequest.email)?.run { throw AlreadyRegisteredEmailException() }
    }
}