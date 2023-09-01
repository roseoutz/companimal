package com.companimal.token.port

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.companimal.kms.infrastructure.persistence.ServerKeyRepository
import com.companimal.token.domain.port.CreateTokenPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreateTokenPortTest @Autowired constructor(
    private val createTokenPort: CreateTokenPort,
    private val serverKeyRepository: ServerKeyRepository
){

    @Test
    fun `should create JWT Token`() {
        val payloads = mapOf(
            "username" to "test",
            "age" to 12,
            "role" to "admin"
        )
        val token = createTokenPort.createToken(payloads)

        val serverKey = serverKeyRepository.findByIsDeleted()!!

        val decoded = JWT.require(Algorithm.HMAC512(serverKey.privateKey)).build()
            .verify(token.token)

        val payloadData = decoded.claims["data"]?.asMap()

        Assertions.assertNotNull(token)
        Assertions.assertEquals(payloads["username"], payloadData?.get("username"))
    }
}