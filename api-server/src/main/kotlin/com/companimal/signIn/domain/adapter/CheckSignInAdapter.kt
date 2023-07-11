package com.companimal.signIn.domain.adapter

import com.companimal.signIn.domain.port.CheckSignInPort
import org.springframework.stereotype.Service

@Service
class CheckSignInAdapter(): CheckSignInPort {
    override fun checkLogin(token: String) {
        TODO("Not yet implemented")
    }
}