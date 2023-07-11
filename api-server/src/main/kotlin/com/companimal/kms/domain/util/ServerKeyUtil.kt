package com.companimal.kms.domain.util

import java.security.KeyPair
import java.security.KeyPairGenerator

object ServerKeyUtil {

    fun generateServerKey(algorithm: String, keySize: Int): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance(algorithm)
        keyPairGenerator.initialize(keySize)
        return keyPairGenerator.generateKeyPair()
    }
}