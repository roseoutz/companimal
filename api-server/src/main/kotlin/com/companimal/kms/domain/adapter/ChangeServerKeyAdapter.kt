package com.companimal.kms.domain.adapter

import com.companimal.common.domain.util.Base64Util
import com.companimal.kms.domain.dto.ServerKey
import com.companimal.kms.domain.persistence.ServerKeyReader
import com.companimal.kms.domain.persistence.ServerKeyWriter
import com.companimal.kms.domain.port.ChangeServerKeyPort
import com.companimal.kms.domain.util.ServerKeyUtil
import org.springframework.stereotype.Service

@Service
class ChangeServerKeyAdapter(
    private val serverKeyReader: ServerKeyReader,
    private val serverKeyWriter: ServerKeyWriter,
): ChangeServerKeyPort {

    override fun changeServerKey() {
        deleteServerKeyIfExist()

        val keyPair = ServerKeyUtil.generateServerKey(getKeyAlgorithm(), getKeySize())
        serverKeyWriter.addServerKey(
            ServerKey(
                privateKey = Base64Util.encodeToBase64(keyPair.private.encoded),
                publicKey = Base64Util.encodeToBase64(keyPair.public.encoded),
                keySize = getKeySize(),
                algorithm = getKeyAlgorithm(),
            )
        )
    }

    private fun deleteServerKeyIfExist() {
        if (serverKeyReader.findActiveServerKey() != null) {
            serverKeyWriter.deleteActiveServerKey()
        }
    }

    private fun getKeySize(): Int = 2048

    private fun getKeyAlgorithm(): String = "RSA"
}