package com.companimal.signIn.domain.adapter

import com.companimal.signIn.domain.port.SignInByTokenPort
import org.springframework.stereotype.Service

@Service
class SignInByTokenAdapter(): SignInByTokenPort {
    override fun signIn(token: String) {
        TODO("Not yet implemented")
    }
}