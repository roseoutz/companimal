package com.companimal.domain.member.service

import com.companimal.domain.member.command.MemberCommand
import com.companimal.domain.member.constants.MemberStatus
import com.companimal.infrastructure.member.persistence.MemberEntity
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
){

    private fun getMemberEntity(): MemberEntity {
        return MemberEntity(
            email = "test@test.com",
            password = "test1234",
            salt = "test",
            confirm = false,
            status = MemberStatus.IN_ACTIVE
        )
    }

    @DisplayName("사용자가 정상 등록된다.")
    @Test
    fun `should create member`() {
        val memberCreateCommand = MemberCommand.Companion.MemberCreateCommand("test@test.com", "test1234")

        memberService.createMember(memberCreateCommand)

        val saved = memberRepository.findByEmail(memberCreateCommand.email)
        Assertions.assertNotNull(saved)
    }

    @DisplayName("사용자를 ID 값으로 정상 조회된다.")
    @Test
    fun `should find member by id`() {
        // given
        val memberEntity = getMemberEntity()
        val saved = memberRepository.save(memberEntity)

        // when
        val member = memberService.getMember(saved.id!!)

        // then
        Assertions.assertEquals(memberEntity.email, member.email)
    }

    @DisplayName("사용자를 Email 으로 정상 조회된다.")
    @Test
    fun `should find member by email`() {
        // given
        val memberEntity = getMemberEntity()
        val saved = memberRepository.save(memberEntity)

        // when
        val member = memberService.getMemberByEmail(memberEntity.email)

        // then
        Assertions.assertEquals(saved.id, member.id)
    }
}