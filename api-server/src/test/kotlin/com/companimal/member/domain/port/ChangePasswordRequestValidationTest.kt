package com.companimal.member.domain.port

import jakarta.validation.Validation
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ChangePasswordRequestValidationTest {

    @Test
    fun `should pass validation`() {
        val changePasswordRequest = ChangePasswordRequest(
            id = 1L,
            oldPassword = "test",
            newPassword = "test"
        )

        val violation = validator.validate(changePasswordRequest)

        Assertions.assertTrue(violation.isEmpty())
    }

    @Test
    fun `should throw id is invalid`() {
        val changePasswordRequest = ChangePasswordRequest(
            id = -1L,
            oldPassword = "test",
            newPassword = "test"
        )

        val violation = validator.validate(changePasswordRequest)

        Assertions.assertTrue(violation.isNotEmpty())
        violation.forEach { Assertions.assertEquals(it.message, "0보다 커야 합니다")}
    }

    @Test
    fun `should throw old password is null`() {
        val changePasswordRequest = ChangePasswordRequest(
            id = 1L,
            oldPassword = "",
            newPassword = "test"
        )

        val violation = validator.validate(changePasswordRequest)

        Assertions.assertTrue(violation.isNotEmpty())
        violation.forEach { Assertions.assertEquals(it.message, "공백일 수 없습니다")}
    }

    @Test
    fun `should throw new password is null`() {
        val changePasswordRequest = ChangePasswordRequest(
            id = 1L,
            oldPassword = "test",
            newPassword = ""
        )

        val violation = validator.validate(changePasswordRequest)

        Assertions.assertTrue(violation.isNotEmpty())
        violation.forEach { Assertions.assertEquals(it.message, "공백일 수 없습니다")}
    }

    companion object {
        private val factory = Validation.buildDefaultValidatorFactory()
        private val validator = factory.validator

        @AfterAll
        fun close() {
            factory.close()
        }
    }
}