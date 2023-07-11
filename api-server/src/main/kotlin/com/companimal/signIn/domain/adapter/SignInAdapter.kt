package com.companimal.signIn.domain.adapter

import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.signIn.domain.port.SignInRequest
import com.companimal.signIn.domain.port.SignInPort
import org.springframework.stereotype.Service

@Service
class SignInAdapter(
    private val memberReader: MemberReader,
    private val hashEncoderPort: HashEncoderPort,
): SignInPort {

    override fun signIn(signInRequest: SignInRequest) {
        TODO("Not yet implemented")
    }
}