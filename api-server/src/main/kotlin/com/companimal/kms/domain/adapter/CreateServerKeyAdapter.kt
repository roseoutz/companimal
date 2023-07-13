package com.companimal.kms.domain.adapter

import com.companimal.common.domain.util.Base64Util
import com.companimal.kms.domain.dto.ServerKey
import com.companimal.kms.domain.exception.AlreadyExistActiveServerKeyException
import com.companimal.kms.domain.persistence.ServerKeyReader
import com.companimal.kms.domain.persistence.ServerKeyWriter
import com.companimal.kms.domain.port.CreateServerKeyPort
import com.companimal.kms.domain.util.ServerKeyUtil
import org.springframework.stereotype.Service

@Service
class CreateServerKeyAdapter(
    private val serverKeyReader: ServerKeyReader,
    private val serverKeyWriter: ServerKeyWriter,
): CreateServerKeyPort {
    override fun createServerKey() {
        checkActiveServerKeyExist()

        serverKeyWriter.addServerKey(createNewServerKey())
    }

    private fun createNewServerKey(): ServerKey {
        val keyPair = ServerKeyUtil.generateServerKey(getKeyAlgorithm(), getKeySize())
        return ServerKey(
            privateKey = Base64Util.encodeToBase64(keyPair.private.encoded),
            publicKey = Base64Util.encodeToBase64(keyPair.public.encoded),
            keySize = getKeySize(),
            algorithm = getKeyAlgorithm(),
        )
    }

    private fun checkActiveServerKeyExist() {
        val serverKey = serverKeyReader.findActiveServerKey()

        if (serverKey != null) {
            throw AlreadyExistActiveServerKeyException()
        }
    }

    private fun getKeySize(): Int = 2048

    private fun getKeyAlgorithm(): String = "RSA"
}