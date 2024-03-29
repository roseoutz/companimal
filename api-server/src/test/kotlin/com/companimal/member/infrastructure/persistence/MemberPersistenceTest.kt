package com.companimal.member.infrastructure.persistence

import com.companimal.AbstractDataJpaTest
import com.companimal.member.domain.constants.MemberStatus
import com.companimal.member.MemberFixture
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

@Transactional
class MemberPersistenceTest(
    @Autowired
    private val memberRepository: MemberRepository
): AbstractDataJpaTest() {

    @DisplayName("멤버 등록이 정상적으로 처리된다.")
    @Test
    fun `should member save`() {
        val entity = memberRepository.save(MemberFixture.memberEntity())

        Assertions.assertNotNull(entity.id)
    }

    @DisplayName("등록된 사용자를 조회한다.")
    @Test
    fun `should search member`() {
        val entity = memberRepository.save(MemberFixture.memberEntity())

        val findOne = memberRepository.findByIdOrNull(entity.id!!) ?: Assertions.fail("사용자 등록 실패")

        Assertions.assertEquals("zeedoutladzz@gmail.com", findOne.email)
    }

    @DisplayName("등록된 사용자의 비밀번호를 변경한다.")
    @Test
    fun `should change password`() {
        val entity = memberRepository.save(MemberFixture.memberEntity())

        entity.password = "test4321"
        memberRepository.save(entity)


        val updated = memberRepository.findByIdOrNull(entity.id!!) ?: Assertions.fail("사용자 등록 실패")
        Assertions.assertEquals(entity.password, updated.password)
    }

    @DisplayName("등록된 사용자를 삭제한다.")
    @Test
    fun `should delete member`() {
        val entity = memberRepository.save(MemberFixture.memberEntity())

        entity.deleteMember()
        val saved = memberRepository.save(entity)

        Assertions.assertEquals(MemberStatus.DELETED, saved.status)
    }

}