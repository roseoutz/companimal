package com.companimal.auth.domain.adapter

import com.companimal.auth.domain.port.CheckSignInUseCasePort
import org.springframework.stereotype.Service

@Service
class CheckSignInUseCaseAdapter(): CheckSignInUseCasePort {
    override fun checkLogin(token: String) {
        TODO("Not yet implemented")
    }
}