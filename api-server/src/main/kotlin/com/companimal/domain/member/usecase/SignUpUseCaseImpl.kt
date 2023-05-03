package com.companimal.domain.member.usecase

import com.companimal.domain.crypto.service.HashEncoderService
import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.persistence.MemberStore
import org.springframework.stereotype.Service

@Service
class SignUpUseCaseImpl(
    private val memberStore: MemberStore,
    private val hashEncoderService: HashEncoderService,
): SignUpUseCase {
    override fun signUp(signUpRequest: SignUpRequest) {
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
}