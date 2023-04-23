package com.companimal.application.crypto.adapter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PasswordEncoderAdapterTest {

    private val passwordEncoderAdapter: PasswordEncoderAdapter = PasswordEncoderAdapter("sha-512")

    @Test
    fun `should encode`() {
        val plainText = "test1234"

        val encoded = passwordEncoderAdapter.encode(plainText)

        Assertions.assertEquals(
            "2BBE0C48B91A7D1B8A6753A8B9CBE1DB16B84379F3F91FE115621284DF7A48F1CD71E9BEB90EA614C7BD924250AA9E446A866725E685A65DF5D139A5CD180DC9",
            encoded.uppercase()
        )
    }

    @Test
    fun `should encode with salt`() {
        val plainText = "test1234"
        val salt = "testSaltValue"

        val encoded = passwordEncoderAdapter.encode(plainText, salt)


        Assertions.assertEquals(
            "C1D0C7301A1A82D10CCFE379E2FC47BABB6AF17EF2C9B6570FBB9BE5FFC4BCA5471E6A102181E6B77095298D1A60912613889EBABC0EE84DA937564A30475101",
            encoded.uppercase()
        )
    }
}