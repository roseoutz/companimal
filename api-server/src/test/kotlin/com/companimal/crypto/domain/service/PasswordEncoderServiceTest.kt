package com.companimal.crypto.domain.service

import com.companimal.crypto.domain.adapter.PasswordEncoderAdapter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.security.SecureRandom

class PasswordEncoderServiceTest {

    private val passwordEncoderService: PasswordEncoderAdapter = PasswordEncoderAdapter("sha-512", 16)

    @Test
    fun `should generated random value`() {
        val secureRandom = SecureRandom.getInstanceStrong()

        val byteArray = ByteArray(16)

        secureRandom.nextBytes(byteArray)
        val sb = StringBuffer()
        for (byte in byteArray) {
            sb.append(String.format("%02x", byte))
        }
        Assertions.assertEquals(32, sb.toString().length)
    }

    @Test
    fun `should generate random value by service`() {
        val salt = passwordEncoderService.getSaltValue()

        Assertions.assertEquals(16, salt.length)
    }
    @Test
    fun `should encode`() {
        val plainText = "test1234"

        val encoded = passwordEncoderService.encode(plainText)

        Assertions.assertEquals(
            "2BBE0C48B91A7D1B8A6753A8B9CBE1DB16B84379F3F91FE115621284DF7A48F1CD71E9BEB90EA614C7BD924250AA9E446A866725E685A65DF5D139A5CD180DC9",
            encoded.uppercase()
        )
    }

    @Test
    fun `should encode with salt`() {
        val plainText = "TestPassword123!!"
        val salt = "salt1234"

        val encoded = passwordEncoderService.encode(plainText, salt)


        Assertions.assertEquals(
            "26f6430c47cadc8a5a46acc894901775a9bca06a6d2eb25fe1f6d5c3c4c5d59cd963efd7f5a36eb7a61b101ec0410f7c9c36c1bbe18f22f9b0ca997d6296cc19",
            encoded
        )
    }
}