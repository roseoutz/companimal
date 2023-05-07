package com.companimal.domain.member.port

import com.companimal.domain.common.constant.CommonErrorCode
import jakarta.validation.Validation
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SignUpRequestValidationTest {

    @Test
    fun `should pass validation`() {
        val signUpRequest = SignUpRequest(email = "test", password = "test")

        val violation = validator.validate(signUpRequest)

        Assertions.assertTrue(violation.isEmpty())
    }

    @Test
    fun `should throw email is null`() {
        val signUpRequest = SignUpRequest(email = "", password = "test")

        val violation = validator.validate(signUpRequest)

        Assertions.assertTrue(violation.isNotEmpty())
        violation.forEach { Assertions.assertEquals(it.message, "공백일 수 없습니다")}
    }

    @Test
    fun `should throw password is null`() {
        val signUpRequest = SignUpRequest(email = "test", password = "")

        val violation = validator.validate(signUpRequest)

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