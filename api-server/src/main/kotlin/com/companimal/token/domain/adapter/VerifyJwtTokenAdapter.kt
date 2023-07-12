package com.companimal.token.domain.adapter

import com.companimal.token.domain.port.VerifyTokenPort
import org.springframework.stereotype.Service

@Service
class VerifyJwtTokenAdapter(): VerifyTokenPort {
    override fun verifyToken(token: String): Boolean {
        TODO("Not yet implemented")
    }
}