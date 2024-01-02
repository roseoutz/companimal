package com.companimal.kms

import com.companimal.common.domain.util.Base64Util
import com.companimal.kms.domain.dto.ServerKey
import com.companimal.kms.domain.util.ServerKeyUtil
import com.companimal.kms.infrastructure.persistence.ServerKeyEntity
import java.security.KeyPair
import java.security.PrivateKey

object ServerKeyFixture {

    private fun createKeyPair(): KeyPair =
        ServerKeyUtil.generateServerKey("RSA", 2048)

    fun serverKeyEntity(
        id: Long? = 1L,
        isDeleted: Boolean = false,
    ): ServerKeyEntity {
        val keyPair = createKeyPair()
        return ServerKeyEntity(
            privateKey = Base64Util.encodeToBase64(keyPair.private.encoded),
            publicKey = Base64Util.encodeToBase64(keyPair.public.encoded),
            keySize = 2048,
            algorithm = "RSA",
            isDeleted = isDeleted,
            id = id
        )
    }

    fun serverKey(
        id: Long? = 1L,
        isDeleted: Boolean = false,
    ): ServerKey =
        serverKeyEntity(
            id = id,
            isDeleted = isDeleted
        ).toServerKey()

}