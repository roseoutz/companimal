package com.companimal.kms.domain.adapter

import com.companimal.kms.domain.persistence.ServerKeyWriter
import com.companimal.kms.domain.port.ChangeServerKeyPort
import com.companimal.kms.domain.port.CreateServerKeyPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangeServerKeyAdapter(
    private val serverKeyWriter: ServerKeyWriter,
    private val createServerKeyPort: CreateServerKeyPort,
): ChangeServerKeyPort {

    @Transactional
    override fun changeServerKey() {
        deleteActiveServerKey()
        createServerKeyPort.createServerKey()
    }

    private fun deleteActiveServerKey() {
        serverKeyWriter.deleteActiveServerKey()
    }
}