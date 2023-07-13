package com.companimal.common.domain.validation

class EmailValidator {
    companion object {
        fun validate(email: String): Boolean =
                Regex("^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)([.]+)(.{2,})\$").matches(email)
    }
}