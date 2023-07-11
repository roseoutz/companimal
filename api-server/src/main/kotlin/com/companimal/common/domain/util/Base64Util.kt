package com.companimal.common.domain.util

import java.nio.charset.StandardCharsets
import java.util.*

object Base64Util {

    fun encodeToBase64(value: String): String {
        return Base64.getEncoder().encodeToString(value.toByteArray(StandardCharsets.UTF_8))
    }

    fun encodeToBase64(value: ByteArray): String {
        return Base64.getEncoder().encodeToString(value)
    }

    fun decodeFromBase64(value: String): String {
        return Base64.getDecoder().decode(value).toString()
    }
}