package com.companimal.infrastructure.member.persistence

import com.companimal.domain.member.constants.MemberStatus
import com.companimal.infrastructure.AbstractDataJpaTest
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

    private fun getMemberEntity(
        email: String = "test@companimal.com",
        password: String = "test1234",
        salt: String = "testestest",
        confirm: Boolean = false,
    ): MemberEntity = MemberEntity(
        email = email,
        password = password,
        salt = salt,
        confirm = confirm,
    )

    @DisplayName("멤버 등록이 정상적으로 처리된다.")
    @Test
    fun `should member save`() {
        val entity = memberRepository.save(getMemberEntity())

        Assertions.assertNotNull(entity.id)
    }

    @DisplayName("등록된 사용자를 조회한다.")
    @Test
    fun `should search member`() {
        val entity = memberRepository.save(getMemberEntity())

        val findOne = memberRepository.findByIdOrNull(entity.id!!) ?: Assertions.fail("사용자 등록 실패")

        Assertions.assertEquals("test@companimal.com", findOne.email)
    }

    @DisplayName("등록된 사용자의 비밀번호를 변경한다.")
    @Test
    fun `should change password`() {
        val entity = memberRepository.save(getMemberEntity())

        entity.password = "test4321"
        memberRepository.save(entity)


        val updated = memberRepository.findByIdOrNull(entity.id!!) ?: Assertions.fail("사용자 등록 실패")
        Assertions.assertEquals(entity.password, updated.password)
    }

    @DisplayName("등록된 사용자를 삭제한다.")
    @Test
    fun `should delete member`() {
        val entity = memberRepository.save(getMemberEntity())

        entity.deleteMember()
        val saved = memberRepository.save(entity)

        Assertions.assertEquals(MemberStatus.DELETED, saved.status)
    }

}