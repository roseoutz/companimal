package com.companimal.member.domain.port

import com.companimal.auth.member.domain.constants.MemberStatus
import com.companimal.auth.member.domain.port.DeleteMemberUseCasePort
import com.companimal.auth.member.infrastructure.persistence.MemberEntity
import com.companimal.auth.member.infrastructure.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class DeleteMemberUseCasePortTest @Autowired constructor(
    private val deleteMemberUseCasePort: DeleteMemberUseCasePort,
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

        deleteMemberUseCasePort.delete(savedEntity.id!!)

        val entity = memberRepository.findByIdOrNull(savedEntity.id!!) ?: Assertions.fail()

        Assertions.assertEquals(MemberStatus.DELETED, entity.status)

    }

}