package com.companimal.auth.member.domain.port

import com.companimal.auth.member.domain.exception.NoSuchMemberException
import com.companimal.auth.member.infrastructure.persistence.MemberEntity
import com.companimal.auth.member.infrastructure.persistence.MemberRepository
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
        val memberEntity = MemberEntity(
            email = "test@test.com",
            password = "password",
            salt = "salt"
        )
        val savedEntity = memberRepository.save(memberEntity)

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