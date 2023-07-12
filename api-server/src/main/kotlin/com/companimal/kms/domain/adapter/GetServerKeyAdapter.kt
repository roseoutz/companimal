package com.companimal.kms.domain.adapter

import com.companimal.kms.domain.dto.ServerKey
import com.companimal.kms.domain.persistence.ServerKeyReader
import com.companimal.kms.domain.port.GetServerKeyPort
import org.springframework.stereotype.Service

@Service
class GetServerKeyAdapter(
    private val serverKeyReader: ServerKeyReader,
): GetServerKeyPort {

    override fun getServerKey(): ServerKey? =
        serverKeyReader.findActiveServerKey()
}