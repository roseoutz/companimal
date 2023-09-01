package com.companimal.kms.domain.port

import com.companimal.kms.ServerKeyFixture
import com.companimal.kms.domain.exception.ActiveServerKeyNotExistException
import com.companimal.kms.infrastructure.persistence.ServerKeyRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChangeServerKeyPortTest @Autowired constructor(
    private val changeServerKeyPort: ChangeServerKeyPort,
    private val serverKeyRepository: ServerKeyRepository,
) {

    @Test
    fun `should throw empty server key`() {
        Assertions.assertDoesNotThrow { changeServerKeyPort.changeServerKey() }
    }

    @Test
    fun `should change server key`() {
        val serverKey = serverKeyRepository.save(ServerKeyFixture.serverKeyEntity())

        changeServerKeyPort.changeServerKey()

        val changed = serverKeyRepository.findByIsDeleted()

        Assertions.assertNotEquals(serverKey.privateKey, changed!!.privateKey)
    }
}