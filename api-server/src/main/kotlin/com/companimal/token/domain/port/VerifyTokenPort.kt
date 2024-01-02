package com.companimal.token.domain.port

import com.companimal.token.domain.dto.VerifiedDecodedToken

interface VerifyTokenPort {

    fun verifyToken(token: String): VerifiedDecodedToken
}