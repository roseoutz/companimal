package com.companimal.application.crypto.constants

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HashAlgorithmTest {

    @Test
    fun `get hash algorithm enum from string value test`() {
        val hashString = "sha-512"

        val algorithm = HashAlgorithm.of(hashString)

        Assertions.assertEquals(HashAlgorithm.SHA512, algorithm)
    }

    @Test
    fun `not exist hash algorithm enum from string value`() {
        val hashString = "sha-1"

        Assertions.assertThrows(NoSuchElementException::class.java) { HashAlgorithm.of(hashString) }
    }
}