package com.companimal.domain.common.validation

class EmailValidator {
    companion object {
        fun validate(email: String): Boolean = (email != null && email != "") && email.matches(
                Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")
            )
    }
}