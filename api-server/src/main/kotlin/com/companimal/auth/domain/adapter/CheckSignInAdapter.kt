package com.companimal.auth.domain.adapter

import com.companimal.auth.domain.exception.TokenInvalidException
import com.companimal.auth.domain.persistence.TokenPublishHistoryReader
import com.companimal.auth.domain.port.CheckSignInPort
import com.companimal.token.domain.port.VerifyTokenPort
import org.springframework.stereotype.Service

@Service
class CheckSignInAdapter(
    private val verifyTokenPort: VerifyTokenPort,
    private val tokenPublishHistoryReader: TokenPublishHistoryReader,
): CheckSignInPort {
    override fun checkLogin(token: String) {
        val verifiedDecodedToken = verifyTokenPort.verifyToken(token)

        if (tokenPublishHistoryReader.getTokenPublishHistory(verifiedDecodedToken.id) == null) {
            throw TokenInvalidException()
        }
    }
}