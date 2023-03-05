package com.companimal.domain

import com.companimal.common.AbstractDataJpaTest
import com.companimal.domain.member.MemberEntity
import com.companimal.infrastructure.member.MemberRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

@Transactional
class MemberPersistenceTest(
    @Autowired
    private val memberRepository: MemberRepository
): AbstractDataJpaTest() {


    @Test
    fun `should member save`() {
        val entity = saveMember(getMemberEntity())

        Assertions.assertNotNull(entity.id)
    }

    @Test
    fun `should search member`() {
        val entity = saveMember(getMemberEntity())

        val findOne = memberRepository.findByIdOrNull(entity.id) ?: Assertions.fail()

        Assertions.assertEquals("test@companimal.com", findOne.email)
    }


    private fun saveMember(memberEntity: MemberEntity): MemberEntity {
        return memberRepository.save(memberEntity)
    }

    fun getMemberEntity(): MemberEntity = MemberEntity(
        email = "test@companimal.com",
        password = "test1234",
        confirm = false
    )
}