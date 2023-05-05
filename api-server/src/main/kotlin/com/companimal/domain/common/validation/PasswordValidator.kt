package com.companimal.domain.common.validation

class PasswordValidator {

    companion object {
        fun validate(value: String): Boolean = value != null && value != "" &&
                Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$").matches(value)
    }
}