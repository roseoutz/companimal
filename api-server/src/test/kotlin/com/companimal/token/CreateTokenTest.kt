package com.companimal.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.companimal.common.domain.util.Base64Util
import com.companimal.kms.ServerKeyFixture
import com.companimal.token.domain.constants.TokenConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.net.InetAddress
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class CreateTokenTest {

    private fun createToken(): String {
        val payloads = mapOf<String, Any>(
            "username" to "test",
            "age" to 12,
            "role" to "admin"
        )

        val serverKey = ServerKeyFixture.serverKeyEntity()
        val algorithm = Algorithm.HMAC512(serverKey.privateKey)
        val jwtId = getUniqueId()

        val now = LocalDateTime.now()
        val issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant())
        val expiredAt = Date.from(now.plusDays(1L).atZone(ZoneId.systemDefault()).toInstant())

        return JWT.create()
            .withHeader(mapOf(TokenConstants.TOKEN_SIGN_KEY_ID.value to 1))
            .withIssuer(getHostname())
            .withJWTId(jwtId)
            .withClaim(TokenConstants.TOKEN_CLAIM_KEY.value, payloads)
            .withIssuedAt(issuedAt)
            .withExpiresAt(expiredAt)
            .sign(algorithm)
    }

    private fun getUniqueId(): String =
        Base64Util.encodeToBase64(getHostname() + UUID.randomUUID().toString())

    private fun getHostname(): String =
        InetAddress.getLocalHost().hostName

    @Test
    fun `should create JWT Token`() {
        val token = createToken()

        Assertions.assertNotNull(token)
    }

    @Test
    fun `should decode JWT token`() {
        val token = createToken()

        val decodedToken = JWT.decode(token)

        Assertions.assertEquals(getHostname(), decodedToken.issuer)
    }

    @Test
    fun `should verify JWT Token`() {
        val payloads = mapOf<String, Any>(
            "username" to "test",
            "age" to 12,
            "role" to "admin"
        )

        val serverKey = ServerKeyFixture.serverKeyEntity()
        val algorithm = Algorithm.HMAC512(serverKey.privateKey)
        val jwtId = getUniqueId()

        val now = LocalDateTime.now()
        val issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant())
        val expiredAt = Date.from(now.plusDays(1L).atZone(ZoneId.systemDefault()).toInstant())

        val token = JWT.create()
            .withHeader(mapOf(TokenConstants.TOKEN_SIGN_KEY_ID.value to 1))
            .withIssuer(getHostname())
            .withJWTId(jwtId)
            .withClaim(TokenConstants.TOKEN_CLAIM_KEY.value, payloads)
            .withIssuedAt(issuedAt)
            .withExpiresAt(expiredAt)
            .sign(algorithm)

        val verifier = JWT.require(algorithm).build()

        val decoded = verifier.verify(token)

        Assertions.assertEquals(getHostname(), decoded.issuer)
    }

    @Test
    fun `should verify fail modified JWT Token`() {
        val payloads = mapOf<String, Any>(
            "username" to "test",
            "age" to 12,
            "role" to "admin"
        )

        val serverKey = ServerKeyFixture.serverKeyEntity()
        val algorithm = Algorithm.HMAC512(serverKey.privateKey)
        val jwtId = getUniqueId()

        val now = LocalDateTime.now()
        val issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant())
        val expiredAt = Date.from(now.plusDays(1L).atZone(ZoneId.systemDefault()).toInstant())

        val token = JWT.create()
            .withHeader(mapOf(TokenConstants.TOKEN_SIGN_KEY_ID.value to 1))
            .withIssuer(getHostname())
            .withJWTId(jwtId)
            .withClaim(TokenConstants.TOKEN_CLAIM_KEY.value, payloads)
            .withIssuedAt(issuedAt)
            .withExpiresAt(expiredAt)
            .sign(algorithm) + "aaa"

        val verifier = JWT.require(algorithm).build()

        Assertions.assertThrows(SignatureVerificationException::class.java) { verifier.verify(token) }
    }
}