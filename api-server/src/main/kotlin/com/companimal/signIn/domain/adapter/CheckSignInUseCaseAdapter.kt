package com.companimal.signIn.domain.adapter

import com.companimal.signIn.domain.port.CheckSignInUseCasePort
import org.springframework.stereotype.Service

@Service
class CheckSignInUseCaseAdapter(): CheckSignInUseCasePort {
    override fun checkLogin(token: String) {
        TODO("Not yet implemented")
    }
}