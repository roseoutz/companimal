package com.companimal.domain.crypto.service

import com.companimal.domain.crypto.constants.HashAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom

@Service
class PasswordEncoderService(
    @Value("\${companial.password.encode.algorithm:sha-512}")
    private val passwordAlgorithm: String,

    @Value("\${companimal.password.encode.salt.length:32}")
    private val passwordSaltLength: Int,
): HashEncoderService {

    override fun getSaltValue(): String {
        val secureRandom = SecureRandom.getInstanceStrong()

        val byteArray = ByteArray((passwordSaltLength/2))

        secureRandom.nextBytes(byteArray)

        return byteToString(byteArray)
    }

    private fun byteToString(byteArray: ByteArray): String {
        val sb = StringBuffer()
        for (byte in byteArray) {
            sb.append(String.format("%02x", byte))
        }
        return sb.toString()
    }

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