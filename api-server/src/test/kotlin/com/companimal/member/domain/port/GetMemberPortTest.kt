package com.companimal.member.domain.port

import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.infrastructure.persistence.MemberRepository
import com.companimal.member.MemberFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class GetMemberPortTest @Autowired constructor(
    private val getMemberPort: GetMemberPort,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should get by user by id`() {
        val memberEntity = MemberFixture.memberEntity()
        val savedEntity = memberRepository.save(memberEntity)

        val member = getMemberPort.get(savedEntity.id!!)

        Assertions.assertNotNull(member)
    }


    @Test
    fun `should getById throw NoSuchMemberException`() {
        val id = 1L

        Assertions.assertThrows(NoSuchMemberException::class.java) {
            getMemberPort.get(id)
        }
    }
}