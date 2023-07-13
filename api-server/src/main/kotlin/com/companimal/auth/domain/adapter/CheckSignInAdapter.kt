package com.companimal.auth.domain.adapter

import com.companimal.auth.domain.port.CheckSignInPort
import org.springframework.stereotype.Service

@Service
class CheckSignInAdapter(): CheckSignInPort {
    override fun checkLogin(token: String) {
        TODO("Not yet implemented")
    }
}