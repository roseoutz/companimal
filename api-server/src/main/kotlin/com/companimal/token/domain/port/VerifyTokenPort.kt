package com.companimal.token.domain.port

interface VerifyTokenPort {

    fun verifyToken(token: String): Boolean
}