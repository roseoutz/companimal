package com.companimal.domain.crypto.service

interface HashEncoderService {

    fun getSaltValue(): String

    fun encode(plainText: String): String

    fun encode(plainText: String, salt: String): String

}