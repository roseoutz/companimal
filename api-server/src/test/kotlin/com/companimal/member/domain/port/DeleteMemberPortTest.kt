package com.companimal.member.domain.port

import com.companimal.member.domain.constants.MemberStatus
import com.companimal.member.infrastructure.persistence.MemberRepository
import com.companimal.member.MemberFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class DeleteMemberPortTest @Autowired constructor(
    private val deleteMemberPort: DeleteMemberPort,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should delete member`() {
        val memberEntity = MemberFixture.memberEntity()
        val savedEntity = memberRepository.save(memberEntity)

        deleteMemberPort.delete(savedEntity.id!!)

        val entity = memberRepository.findByIdOrNull(savedEntity.id!!) ?: Assertions.fail()

        Assertions.assertEquals(MemberStatus.DELETED, entity.status)

    }

}