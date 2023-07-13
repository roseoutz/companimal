package com.companimal.auth.domain.port

import com.companimal.auth.domain.exception.PasswordNotMatchException
import com.companimal.kms.ServerKeyFixture
import com.companimal.kms.infrastructure.persistence.ServerKeyRepository
import com.companimal.member.MemberFixture
import com.companimal.member.infrastructure.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class SignInPortTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val signInPort: SignInPort,
    private val serverKeyRepository: ServerKeyRepository,
) {

    @Test
    fun `should success sign in`() {
        val member = MemberFixture.memberEntity()
        memberRepository.save(member)
        val serverKey = ServerKeyFixture.serverKeyEntity()
        serverKeyRepository.save(serverKey)

        val signInRequest = SignInRequest(member.email, "TestPassword123!!")
        val signInResponse = signInPort.signIn(signInRequest)

        Assertions.assertNotNull(signInResponse.token)
    }

    @Test
    fun `should fail sign in`() {
        val member = MemberFixture.memberEntity(password = "TestPassword123!")
        memberRepository.save(member)

        val signInRequest = SignInRequest(member.email, member.password!!)

        Assertions.assertThrows(PasswordNotMatchException::class.java) { signInPort.signIn(signInRequest) }
    }
}