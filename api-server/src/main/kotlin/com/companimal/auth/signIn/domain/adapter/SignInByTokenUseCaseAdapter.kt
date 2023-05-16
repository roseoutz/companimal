package com.companimal.auth.signIn.domain.adapter

import com.companimal.auth.signIn.domain.port.SignInByTokenUseCasePort
import org.springframework.stereotype.Service

@Service
class SignInByTokenUseCaseAdapter(): com.companimal.auth.signIn.domain.port.SignInByTokenUseCasePort {
    override fun signIn(token: String) {
        TODO("Not yet implemented")
    }
}