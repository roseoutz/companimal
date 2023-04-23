package com.companimal.application.crypto.adapter

import com.companimal.application.crypto.constants.HashAlgorithm
import com.companimal.application.crypto.port.`in`.HashEncoderPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@Service
class PasswordEncoderAdapter(
    @Value("\${companial.password.encode.algorithm:sha512}")
    private val passwordAlgorithm: String,
):HashEncoderPort {

    override fun encode(plainText: String): String {
        val algorithm = HashAlgorithm.of(passwordAlgorithm)
        val messageDigest = MessageDigest.getInstance(algorithm.getValue())

        return encodeToHex(messageDigest.digest(plainText.toByteArray(StandardCharsets.UTF_8)))
    }

    override fun encode(plainText: String, salt: String): String {
        return encode(plainText + salt)
    }

    private fun encodeToHex(bytes: ByteArray): String {
        return bytes.joinToString(separator = "") { "%02x".format(it) }
    }
}