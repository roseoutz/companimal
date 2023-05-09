package com.companimal.common.domain.validation

import com.companimal.common.domain.validation.EmailValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EmailValidatorTest {

    @Test
    fun `should validate empty email`() {
        val email = ""

        Assertions.assertFalse(EmailValidator.validate(email))
    }

    @Test
    fun `should validate invalid format email`() {
        val email = "test@gmailcom"

        Assertions.assertFalse(EmailValidator.validate(email))
    }

    @Test
    fun `should validate valid format email`() {
        val email = "test43@gmail.com"

        Assertions.assertTrue(EmailValidator.validate(email))
    }
}