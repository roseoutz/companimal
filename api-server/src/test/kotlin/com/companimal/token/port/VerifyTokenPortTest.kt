package com.companimal.token.port

import com.companimal.MockingTestExtension
import com.companimal.kms.ServerKeyFixture
import com.companimal.kms.domain.port.GetServerKeyPort
import com.companimal.token.domain.adapter.CreateJwtTokenAdapter
import com.companimal.token.domain.adapter.VerifyJwtTokenAdapter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito

class VerifyTokenPortTest: MockingTestExtension(){

    @Mock
    private lateinit var getServerKeyPort: GetServerKeyPort


    @Test
    fun `should verify token`() {
        val serverKey = ServerKeyFixture.serverKeyEntity()
        Mockito.`when`(getServerKeyPort.getServerKey()).thenReturn(serverKey.toServerKey())
        val createTokenPort = CreateJwtTokenAdapter(getServerKeyPort)
        val verifyTokenPort = VerifyJwtTokenAdapter(getServerKeyPort)


        val payloads = mapOf(
            "username" to "test",
            "age" to 12,
            "role" to "admin"
        )
        val token = createTokenPort.createToken(payloads)

        val decoded = verifyTokenPort.verifyToken(token.token)

        Assertions.assertEquals("test", decoded.payloads["username"])
        Assertions.assertEquals(12, decoded.payloads["age"])
        Assertions.assertEquals("admin", decoded.payloads["role"])
        Assertions.assertDoesNotThrow() {
            verifyTokenPort.verifyToken(token.token)
        }
    }
}