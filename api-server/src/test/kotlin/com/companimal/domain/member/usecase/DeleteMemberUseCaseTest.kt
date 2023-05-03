package com.companimal.domain.member.usecase

import com.companimal.domain.member.constants.MemberStatus
import com.companimal.infrastructure.member.persistence.MemberEntity
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class DeleteMemberUseCaseTest @Autowired constructor(
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should delete member`() {
        val memberEntity = MemberEntity(
            email = "test@test.com",
            password = "password",
            salt = "salt"
        )
        val savedEntity = memberRepository.save(memberEntity)

        deleteMemberUseCase.delete(DeleteMemberRequest(savedEntity.id!!))

        val entity = memberRepository.findByIdOrNull(savedEntity.id!!) ?: Assertions.fail()

        Assertions.assertEquals(MemberStatus.DELETED, entity.status)

    }

}