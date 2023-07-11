package com.companimal.kms.domain.persistence

import com.companimal.kms.domain.dto.ServerKey

interface ServerKeyWriter {

    fun addServerKey(serverKey: ServerKey)

    fun deleteActiveServerKey()
}