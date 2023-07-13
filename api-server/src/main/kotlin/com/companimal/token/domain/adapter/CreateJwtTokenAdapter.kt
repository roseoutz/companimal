package com.companimal.token.domain.adapter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.companimal.common.domain.util.Base64Util
import com.companimal.kms.domain.port.GetServerKeyPort
import com.companimal.token.domain.constants.TokenConstants
import com.companimal.token.domain.dto.Token
import com.companimal.token.domain.exception.ServerKeyNotFoundException
import com.companimal.token.domain.port.CreateTokenPort
import org.springframework.stereotype.Service
import java.net.InetAddress
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class CreateJwtTokenAdapter(
    private val getServerKeyPort: GetServerKeyPort,
): CreateTokenPort {
    override fun createToken(payloads: Map<String, Any>): Token {

        val serverKey = getServerKeyPort.getServerKey() ?: throw ServerKeyNotFoundException()

        val algorithm = Algorithm.HMAC512(serverKey.privateKey)
        val jwtId = getUniqueId()
        val now = LocalDateTime.now()
        val issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant())
        val expiredAt = Date.from(now.plusMinutes(24 * 60).atZone(ZoneId.systemDefault()).toInstant())

        val token = JWT.create()
            .withHeader(mapOf(
                TokenConstants.TOKEN_SIGN_KEY_ID.value to serverKey.id
            ))
            .withIssuer(getHostname())
            .withJWTId(jwtId)
            .withClaim(TokenConstants.TOKEN_CLAIM_KEY.value, payloads)
            .withIssuedAt(issuedAt)
            .withExpiresAt(expiredAt)
            .sign(algorithm)

        return Token(
            id = jwtId,
            token = token,
            issuedAt = issuedAt,
            expiredAt = expiredAt,
        )
    }

    private fun getUniqueId(): String =
        Base64Util.encodeToBase64(UUID.randomUUID().toString())

    private fun getHostname(): String =
        InetAddress.getLocalHost().hostName

}