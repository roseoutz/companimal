package com.companimal.signIn.domain.adapter

import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.auth.member.domain.persistence.MemberReader
import com.companimal.signIn.domain.port.SignInRequest
import com.companimal.signIn.domain.port.SignInUseCasePort
import org.springframework.stereotype.Service

@Service
class SignInUseCaseAdapter(
    private val memberReader: MemberReader,
    private val hashEncoderPort: HashEncoderPort,
): SignInUseCasePort {

    override fun signIn(signInRequest: SignInRequest) {
        TODO("Not yet implemented")
    }
}