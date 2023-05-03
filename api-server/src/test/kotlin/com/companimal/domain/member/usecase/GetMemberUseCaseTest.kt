package com.companimal.domain.member.usecase

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
class GetMemberUseCaseTest @Autowired constructor(
    private val getMemberUseCase: GetMemberUseCase,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should get by user by id`() {
        val memberEntity = MemberEntity(
            email = "test@test.com",
            password = "password",
            salt = "salt"
        )
        val savedEntity = memberRepository.save(memberEntity)

        val member = getMemberUseCase.get(GetMemberRequest(savedEntity.id!!))

        Assertions.assertNotNull(member)
    }


    @Test
    fun `should getById throw NoSuchMemberException`() {
        val id = 1L

        Assertions.assertThrows(NoSuchMemberException::class.java) {
            getMemberUseCase.get(GetMemberRequest(id))
        }
    }
}