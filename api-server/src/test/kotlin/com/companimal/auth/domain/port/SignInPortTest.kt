package com.companimal.auth.domain.port

import com.companimal.auth.domain.constants.AuthenticationConstants
import com.companimal.auth.domain.exception.PasswordNotMatchException
import com.companimal.kms.ServerKeyFixture
import com.companimal.kms.infrastructure.persistence.ServerKeyRepository
import com.companimal.member.MemberFixture
import com.companimal.member.domain.constants.MemberRole
import com.companimal.member.infrastructure.persistence.MemberRepository
import com.companimal.token.domain.port.VerifyTokenPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.lang.reflect.Member

@Transactional
@SpringBootTest
class SignInPortTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val signInPort: SignInPort,
    private val serverKeyRepository: ServerKeyRepository,
    private val verifyTokenPort: VerifyTokenPort
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
    fun `should contain member role`() {
        val member = MemberFixture.memberEntity()
        memberRepository.save(member)
        val serverKey = ServerKeyFixture.serverKeyEntity()
        serverKeyRepository.save(serverKey)

        val signInRequest = SignInRequest(member.email, "TestPassword123!!")
        val signInResponse = signInPort.signIn(signInRequest)
        val tokenInfo = verifyTokenPort.verifyToken(signInResponse.token)
        val role = tokenInfo.payloads.getValue(AuthenticationConstants.TOKEN_CLAIM_ROLE.value)
        Assertions.assertEquals(MemberRole.USER.toString(), role)
    }


    @Test
    fun `should fail sign in`() {
        val member = MemberFixture.memberEntity(password = "TestPassword123!")
        memberRepository.save(member)

        val signInRequest = SignInRequest(member.email, member.password!!)

        Assertions.assertThrows(PasswordNotMatchException::class.java) { signInPort.signIn(signInRequest) }
    }
}