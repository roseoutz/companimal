package com.companimal.member.domain.port

import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.infrastructure.persistence.MemberEntity
import com.companimal.member.infrastructure.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class GetMemberByEmailUseCasePortTest @Autowired constructor(
    private val getMemberByEmailUseCasePort: GetMemberByEmailUseCasePort,
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

        val member = getMemberByEmailUseCasePort.get(memberEntity.email)

        Assertions.assertNotNull(member)
    }

    @Test
    fun `should getByEmail throw NoSuchMemberException`() {
        val email = "test@test.com"

        Assertions.assertThrows(NoSuchMemberException::class.java) {
            getMemberByEmailUseCasePort.get(email)
        }
    }
}