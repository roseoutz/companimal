package com.companimal.signIn.domain.adapter

import com.companimal.signIn.domain.port.SignInByTokenUseCasePort
import org.springframework.stereotype.Service

@Service
class SignInByTokenUseCaseAdapter(): SignInByTokenUseCasePort {
    override fun signIn(token: String) {
        TODO("Not yet implemented")
    }
}