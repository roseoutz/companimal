package com.companimal.kms.infrastructure.persistence

import com.companimal.kms.domain.dto.ServerKey
import com.companimal.kms.domain.persistence.ServerKeyWriter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class ServerKeyJpaWriter(
    private val serverKeyRepository: ServerKeyRepository
): ServerKeyWriter {

    override fun addServerKey(serverKey: ServerKey) {
        serverKeyRepository.save(ServerKeyEntity.of(serverKey))
    }

    override fun deleteActiveServerKey() =
        serverKeyRepository.findByDeleted()
            .let { it.deleteServerKey() }

}