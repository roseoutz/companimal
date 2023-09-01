package com.companimal.token.domain.adapter

import com.auth0.jwt.JWT
import com.auth0.jwt.RegisteredClaims
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.companimal.kms.domain.port.GetServerKeyPort
import com.companimal.token.domain.dto.VerifiedDecodedToken
import com.companimal.token.domain.exception.AlreadyExpiredTokenException
import com.companimal.token.domain.exception.ServerKeyIdNotMatchException
import com.companimal.token.domain.port.VerifyTokenPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class VerifyJwtTokenAdapter(
    private val getServerKeyPort: GetServerKeyPort,
): VerifyTokenPort {
    override fun verifyToken(token: String): VerifiedDecodedToken {
        val decodedJWT = decodeToken(token)

        isExpiredToken(decodedJWT)

        return convertToVerifiedDecodedToken(decodedJWT)
    }

    private fun decodeToken(token: String): DecodedJWT {
        val serverKey = getServerKeyPort.getServerKey()
        val decodedJWT =  JWT.decode(token)

        if (serverKey.id.toString() != decodedJWT.keyId) {
            throw ServerKeyIdNotMatchException(decodedJWT.keyId)
        }

        val algorithm = Algorithm.HMAC512(serverKey.privateKey)
        val verifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    private fun isExpiredToken(decodedJWT: DecodedJWT) {
        if (Date().after(decodedJWT.expiresAt)) {
            throw AlreadyExpiredTokenException()
        }
    }

    private fun convertToVerifiedDecodedToken(decodedJWT: DecodedJWT): VerifiedDecodedToken =
        VerifiedDecodedToken(
            id = decodedJWT.claims[RegisteredClaims.JWT_ID].toString(),
            payloads = decodedJWT.claims["data"]!!.asMap(),
        )
}