package com.companimal.application.crypto.constants

import java.util.*

enum class HashAlgorithm {
    SHA256("SHA-256"),
    SHA512("SHA-512")
    ;

    constructor(algorithm: String) {
        this.algorithm = algorithm
    }

    private val algorithm: String

    fun getValue(): String {
        return algorithm
    }

    companion object {
        fun of(algorithm: String): HashAlgorithm {
            return values()
                .first { it.getValue() == algorithm.uppercase(Locale.getDefault()) }
        }
    }
}