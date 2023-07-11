package com.companimal.auth.domain.adapter

import com.companimal.auth.domain.port.SignInByTokenPort
import org.springframework.stereotype.Service

@Service
class SignInByTokenAdapter(): SignInByTokenPort {
    override fun signIn(token: String) {
        TODO("Not yet implemented")
    }
}