package com.companimal.kms.domain.port

import com.companimal.kms.domain.dto.ServerKey

interface GetServerKeyPort {

    fun getServerKey(): ServerKey?
}