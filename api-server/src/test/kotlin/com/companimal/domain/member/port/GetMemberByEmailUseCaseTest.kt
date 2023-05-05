package com.companimal.domain.member.port

import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.infrastructure.member.persistence.MemberEntity
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class GetMemberByEmailUseCaseTest @Autowired constructor(
    private val getMemberByEmailUseCase: GetMemberByEmailUseCase,
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

        val member = getMemberByEmailUseCase.get(memberEntity.email)

        Assertions.assertNotNull(member)
    }

    @Test
    fun `should getByEmail throw NoSuchMemberException`() {
        val email = "test@test.com"

        Assertions.assertThrows(NoSuchMemberException::class.java) {
            getMemberByEmailUseCase.get(email)
        }
    }
}