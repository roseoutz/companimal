package com.companimal.auth.domain.adapter

import com.companimal.auth.domain.port.SignInRequest
import com.companimal.auth.domain.port.SignInUseCasePort
import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.member.domain.persistence.MemberReader
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