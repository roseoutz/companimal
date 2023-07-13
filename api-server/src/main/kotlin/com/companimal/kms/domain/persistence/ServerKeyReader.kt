package com.companimal.kms.domain.persistence

import com.companimal.kms.domain.dto.ServerKey

interface ServerKeyReader {

    fun findActiveServerKey(): ServerKey
}