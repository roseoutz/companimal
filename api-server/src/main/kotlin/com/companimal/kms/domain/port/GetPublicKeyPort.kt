package com.companimal.kms.domain.port

import com.companimal.kms.domain.dto.ServerKey

interface GetPublicKeyPort {

    fun getPublicKey(): ServerKey
}