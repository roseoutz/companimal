package com.companimal.auth.signIn.domain.adapter

import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.auth.member.domain.persistence.MemberReader
import org.springframework.stereotype.Service

@Service
class SignInUseCaseAdapter(
    private val memberReader: MemberReader,
    private val hashEncoderPort: HashEncoderPort,
): com.companimal.auth.signIn.domain.port.SignInUseCasePort {

    override fun signIn(signInRequest: com.companimal.auth.signIn.domain.port.SignInRequest) {
        TODO("Not yet implemented")
    }
}