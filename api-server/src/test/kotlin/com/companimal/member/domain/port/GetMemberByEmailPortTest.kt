package com.companimal.member.domain.port

import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.infrastructure.persistence.MemberRepository
import com.companimal.member.MemberFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class GetMemberByEmailPortTest @Autowired constructor(
    private val getMemberByEmailPort: GetMemberByEmailPort,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should get by user by email`() {
        val memberEntity = MemberFixture.memberEntity()
        memberRepository.save(memberEntity)

        val member = getMemberByEmailPort.get(memberEntity.email)

        Assertions.assertNotNull(member)
    }

    @Test
    fun `should getByEmail throw NoSuchMemberException`() {
        val email = "test@test.com"

        Assertions.assertThrows(NoSuchMemberException::class.java) {
            getMemberByEmailPort.get(email)
        }
    }
}