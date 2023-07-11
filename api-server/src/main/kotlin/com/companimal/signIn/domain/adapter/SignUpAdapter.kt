package com.companimal.signIn.domain.adapter

import com.companimal.common.domain.validation.EmailValidator
import com.companimal.common.domain.validation.PasswordValidator
import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.signIn.domain.exception.AlreadyRegisteredMemberException
import com.companimal.member.domain.exception.InvalidFormatEmailException
import com.companimal.member.domain.exception.InvalidFormatPasswordException
import com.companimal.member.domain.port.CheckExistMemberPort
import com.companimal.member.domain.port.CreateMemberPort
import com.companimal.member.domain.port.CreateMemberRequest
import com.companimal.member.domain.port.GetMemberByEmailPort
import com.companimal.signIn.domain.port.SignUpPort
import com.companimal.signIn.domain.port.SignUpRequest
import org.springframework.stereotype.Service

@Service
class SignUpAdapter(
    private val getMemberByEmailPort: GetMemberByEmailPort,
    private val checkExistMemberPort: CheckExistMemberPort,
    private val createMemberPort: CreateMemberPort,
    private val hashEncoderPort: HashEncoderPort,
): SignUpPort {

    override fun signUp(signUpRequest: SignUpRequest) {
        validateSignUpRequest((signUpRequest))

        val salt = hashEncoderPort.getSaltValue()
        val encoded = hashEncoderPort.encode(signUpRequest.password, salt)

        createMemberPort.signUp(
            CreateMemberRequest(
                email = signUpRequest.email,
                password = encoded,
                salt = salt
            )
        )
    }

    private fun validateSignUpRequest(signUpRequest: SignUpRequest) {
        checkRegisteredEmail(signUpRequest.email)
        checkPasswordFormat(signUpRequest.password)
    }

    private fun checkPasswordFormat(password: String) {
        if (!PasswordValidator.validate(password)) {
            throw InvalidFormatPasswordException()
        }
    }

    private fun checkRegisteredEmail(email: String) {
        if (!EmailValidator.validate(email)) {
            throw InvalidFormatEmailException()
        }

        if (checkExistMemberPort.isExistMember(email)) {
            throw AlreadyRegisteredMemberException()
        }
    }
}