package com.companimal.token.port

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.companimal.MockingTestExtension
import com.companimal.kms.ServerKeyFixture
import com.companimal.kms.domain.port.GetServerKeyPort
import com.companimal.kms.infrastructure.persistence.ServerKeyRepository
import com.companimal.token.domain.adapter.CreateJwtTokenAdapter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito


class CreateTokenPortTest: MockingTestExtension() {

    @Mock
    private lateinit var getServerKeyPort: GetServerKeyPort

    @Mock
    private lateinit var serverKeyRepository: ServerKeyRepository

    @Test
    fun `should create JWT Token`() {
        val serverKey = ServerKeyFixture.serverKeyEntity()
        Mockito.`when`(serverKeyRepository.findByIsDeleted()).thenReturn(serverKey)
        Mockito.`when`(getServerKeyPort.getServerKey()).thenReturn(serverKey.toServerKey())
        val createTokenPort = CreateJwtTokenAdapter(getServerKeyPort)

        val payloads = mapOf(
            "username" to "test",
            "age" to 12,
            "role" to "admin"
        )
        val token = createTokenPort.createToken(payloads)

        val activeServerKey = serverKeyRepository.findByIsDeleted()!!

        val decoded = JWT.require(Algorithm.HMAC512(activeServerKey.privateKey)).build()
            .verify(token.token)

        val payloadData = decoded.claims["data"]?.asMap()

        Assertions.assertNotNull(token)
        Assertions.assertEquals(payloads["username"], payloadData?.get("username"))
    }
}