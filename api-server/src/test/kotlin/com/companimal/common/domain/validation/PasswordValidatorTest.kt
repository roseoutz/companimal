package com.companimal.common.domain.validation

import com.companimal.common.domain.validation.PasswordValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PasswordValidatorTest {

    @Test
    fun `should validate empty password`() {
        val password = ""

        Assertions.assertFalse(PasswordValidator.validate(password))
    }

    @Test
    fun `should validate invalid format password`() {
        val password = "test1234"

        Assertions.assertFalse(PasswordValidator.validate(password))
    }

    @Test
    fun `should validate valid format password`() {
        val password = "Test1234@"

        Assertions.assertTrue(PasswordValidator.validate(password))
    }
}