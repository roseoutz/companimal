package com.companimal.domain.member.command

import com.companimal.domain.member.constants.MemberStatus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MemberCommandTest {

    @Test
    fun `should memberSignUpCommand to member`() {
        val memberCreateCommand = MemberCommand.Companion.MemberCreateCommand(
            email = "test@test.com",
            password = "test1234"
        )

        val member = memberCreateCommand.toMember()

        Assertions.assertEquals(memberCreateCommand.email, member.email)
        Assertions.assertEquals(memberCreateCommand.password, member.password)
        Assertions.assertEquals(false, member.confirm)
        Assertions.assertEquals(MemberStatus.IN_ACTIVE, member.status)

    }
}