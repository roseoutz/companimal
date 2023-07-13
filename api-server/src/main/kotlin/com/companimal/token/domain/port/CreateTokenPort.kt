package com.companimal.token.domain.port

import com.companimal.token.domain.dto.Token

interface CreateTokenPort {

    fun createToken(payloads: Map<String, Any>): Token
}