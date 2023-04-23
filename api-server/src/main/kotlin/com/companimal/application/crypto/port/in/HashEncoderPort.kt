package com.companimal.application.crypto.port.`in`

interface HashEncoderPort {

    fun encode(plainText: String): String

    fun encode(plainText: String, salt: String): String

}