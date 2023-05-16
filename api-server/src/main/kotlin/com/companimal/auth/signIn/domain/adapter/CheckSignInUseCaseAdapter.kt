package com.companimal.auth.signIn.domain.adapter

import com.companimal.auth.signIn.domain.port.CheckSignInUseCasePort
import org.springframework.stereotype.Service

@Service
class CheckSignInUseCaseAdapter(): com.companimal.auth.signIn.domain.port.CheckSignInUseCasePort {
    override fun checkLogin(token: String) {
        TODO("Not yet implemented")
    }
}