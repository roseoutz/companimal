package com.companimal.crypto.domain.port

interface HashEncoderPort {

    fun getSaltValue(): String

    fun match(plainText: String, encodeText: String): Boolean

    fun match(plainText: String, salt:String?, encodeText: String): Boolean

    fun encode(plainText: String): String

    fun encode(plainText: String, salt: String): String

}