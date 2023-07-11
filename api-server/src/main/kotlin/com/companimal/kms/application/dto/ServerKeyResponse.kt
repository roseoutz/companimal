package com.companimal.kms.application.dto

import com.companimal.kms.domain.dto.ServerKey

data class ServerKeyResponse(
    val id: Long,
    val publicKey: String
) {

    companion object {
        fun of(serverKey: ServerKey): ServerKeyResponse =
            ServerKeyResponse(
                id = serverKey.id!!,
                publicKey = serverKey.publicKey
            )
    }
}