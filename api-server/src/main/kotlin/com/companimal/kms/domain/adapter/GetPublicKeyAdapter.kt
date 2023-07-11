package com.companimal.kms.domain.adapter

import com.companimal.kms.domain.dto.ServerKey
import com.companimal.kms.domain.persistence.ServerKeyReader
import com.companimal.kms.domain.port.GetPublicKeyPort
import org.springframework.stereotype.Service

@Service
class GetPublicKeyAdapter(
    private val serverKeyReader: ServerKeyReader,
): GetPublicKeyPort {

    override fun getPublicKey(): ServerKey? =
        serverKeyReader.findActiveServerKey()
}