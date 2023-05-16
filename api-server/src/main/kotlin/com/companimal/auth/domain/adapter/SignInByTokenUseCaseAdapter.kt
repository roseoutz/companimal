package com.companimal.auth.domain.adapter

import com.companimal.auth.domain.port.SignInByTokenUseCasePort
import org.springframework.stereotype.Service

@Service
class SignInByTokenUseCaseAdapter(): SignInByTokenUseCasePort {
    override fun signIn(token: String) {
        TODO("Not yet implemented")
    }
}