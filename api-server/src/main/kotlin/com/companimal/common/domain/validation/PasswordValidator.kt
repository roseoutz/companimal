package com.companimal.common.domain.validation

class PasswordValidator {

    companion object {
        fun validate(value: String): Boolean =
                Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$").matches(value)
    }
}