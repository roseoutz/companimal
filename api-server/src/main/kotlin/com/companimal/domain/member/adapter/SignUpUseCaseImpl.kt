package com.companimal.domain.member.adapter

import com.companimal.domain.crypto.service.HashEncoderService
import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.exception.AlreadyRegisteredEmailException
import com.companimal.domain.member.persistence.MemberReader
import com.companimal.domain.member.persistence.MemberStore
import com.companimal.domain.member.port.SignUpRequest
import com.companimal.domain.member.port.SignUpUseCase
import org.springframework.stereotype.Service

@Service
class SignUpUseCaseImpl(
    private val memberReader: MemberReader,
    private val memberStore: MemberStore,
    private val hashEncoderService: HashEncoderService,
): SignUpUseCase {
    override fun signUp(signUpRequest: SignUpRequest) {
        checkRegisteredEmail(signUpRequest)

        val salt = hashEncoderService.getSaltValue()
        val encoded = hashEncoderService.encode(signUpRequest.password, salt)
        memberStore.addMember(
            Member(
                email = signUpRequest.email,
                password = encoded,
                salt = salt,
            )
        )
    }

    private fun checkRegisteredEmail(signUpRequest: SignUpRequest) {
        memberReader.findByEmail(signUpRequest.email)?.run { throw AlreadyRegisteredEmailException() }
    }
}