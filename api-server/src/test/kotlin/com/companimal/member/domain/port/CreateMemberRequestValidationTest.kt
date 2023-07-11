package com.companimal.member.domain.port

import com.companimal.member.test.MemberFixture
import jakarta.validation.Validation
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreateMemberRequestValidationTest {

    @Test
    fun `should pass validation`() {
        val createMemberRequest = MemberFixture.createMemberRequest()

        val violation = validator.validate(createMemberRequest)

        Assertions.assertTrue(violation.isEmpty())
    }

    @Test
    fun `should throw email is null`() {
        val createMemberRequest = MemberFixture.createMemberRequest()

        val violation = validator.validate(createMemberRequest)

        Assertions.assertTrue(violation.isNotEmpty())
        violation.forEach { Assertions.assertEquals(it.message, "공백일 수 없습니다")}
    }

    @Test
    fun `should throw password is null`() {
        val createMemberRequest = MemberFixture.createMemberRequest()

        val violation = validator.validate(createMemberRequest)

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