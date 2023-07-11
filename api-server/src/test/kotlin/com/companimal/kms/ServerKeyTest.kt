package com.companimal.kms

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

class ServerKeyTest {

    @Test
    fun `generate server key`() {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        Assertions.assertEquals("RSA", keyPair.private.algorithm)
        Assertions.assertEquals("RSA", keyPair.public.algorithm)
    }

    @Test
    fun `key convert to String`() {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        val encodedPrivateKey = Base64.getEncoder().encodeToString(keyPair.private.encoded)
        val encodedPublicKey = Base64.getEncoder().encodeToString(keyPair.public.encoded)

        val publicKeySpec = X509EncodedKeySpec(Base64.getDecoder().decode(encodedPublicKey))
        val privateKeySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodedPrivateKey))

        val publicKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec)
        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec)

        Assertions.assertEquals(keyPair.public.toString(), publicKey.toString())
        Assertions.assertEquals(keyPair.private.toString(), privateKey.toString())
    }
}