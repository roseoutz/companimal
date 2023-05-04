package com.companimal.domain.crypto.service

interface HashEncoderService {

    fun getSaltValue(): String

    fun match(plainText: String, encodeText: String): Boolean

    fun match(plainText: String, salt:String?, encodeText: String): Boolean

    fun encode(plainText: String): String

    fun encode(plainText: String, salt: String): String

}