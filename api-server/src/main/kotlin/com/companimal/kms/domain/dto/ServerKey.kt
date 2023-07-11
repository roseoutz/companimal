package com.companimal.kms.domain.dto

import java.time.LocalDateTime

data class ServerKey (
    val id: Long? = null,
    val privateKey: String,
    val publicKey: String,
    val keySize: Int,
    val algorithm: String,
    val isDeleted: Boolean = false,
    val createdDatetime: LocalDateTime? = null,
    val updatedDatetime: LocalDateTime? = null,
)