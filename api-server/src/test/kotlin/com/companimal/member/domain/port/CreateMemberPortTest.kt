package com.companimal.member.domain.port

import com.companimal.member.domain.exception.AlreadyRegisteredEmailException
import com.companimal.member.infrastructure.persistence.MemberRepository
import com.companimal.member.MemberFixture
import com.companimal.member.domain.constants.MemberRole
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class CreateMemberPortTest @Autowired constructor(
    private val createMemberPort: CreateMemberPort,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should signup member`() {
        val createMemberRequest = MemberFixture.createMemberRequest()
        createMemberPort.signUp(createMemberRequest)

        val entity = memberRepository.findByEmail(createMemberRequest.email)

        Assertions.assertNotNull(entity)
        Assertions.assertEquals(MemberRole.USER, entity?.role)
    }

    @Test
    fun `should throw alreadyRegisteredEmail Exception`() {
        val createMemberRequest = MemberFixture.createMemberRequest()
        createMemberPort.signUp(createMemberRequest)

        Assertions.assertThrows(AlreadyRegisteredEmailException::class.java) { createMemberPort.signUp(createMemberRequest) }
    }

}