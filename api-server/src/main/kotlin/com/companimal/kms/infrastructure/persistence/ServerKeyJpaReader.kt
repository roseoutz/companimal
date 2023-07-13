package com.companimal.kms.infrastructure.persistence

import com.companimal.kms.domain.dto.ServerKey
import com.companimal.kms.domain.exception.ActiveServerKeyNotExistException
import com.companimal.kms.domain.persistence.ServerKeyReader
import org.springframework.stereotype.Service

@Service
class ServerKeyJpaReader(
    private val serverKeyRepository: ServerKeyRepository
): ServerKeyReader {
    override fun findActiveServerKey(): ServerKey =
        ( serverKeyRepository.findByIsDeleted() ?: throw ActiveServerKeyNotExistException() )
            .let { it.toServerKey() }
}