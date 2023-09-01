package com.companimal.token.port

import com.companimal.token.domain.port.CreateTokenPort
import com.companimal.token.domain.port.VerifyTokenPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VerifyTokenPortTest @Autowired constructor(
    private val createTokenPort: CreateTokenPort,
    private val verifyTokenPort: VerifyTokenPort
) {

    @Test
    fun `should verify token`() {
        val payloads = mapOf(
            "username" to "test",
            "age" to 12,
            "role" to "admin"
        )
        val token = createTokenPort.createToken(payloads)

        Assertions.assertDoesNotThrow() {
            verifyTokenPort.verifyToken(token.token)
        }
    }
}