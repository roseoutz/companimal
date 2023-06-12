package com.companimal.auth.member.domain.port

import com.companimal.auth.member.domain.exception.AlreadyRegisteredEmailException
import com.companimal.auth.member.infrastructure.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class SignUpPortTest @Autowired constructor(
    private val signUpPort: SignUpPort,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should signup member`() {
        val signUpRequest = SignUpRequest(
            "test@test.com",
            "test1234"
        )
        signUpPort.signUp(signUpRequest)

        val entity = memberRepository.findByEmail(signUpRequest.email)

        Assertions.assertNotNull(entity)
    }

    @Test
    fun `should throw alreadyRegisteredEmail Exception`() {
        val signUpRequest = SignUpRequest(
            "test@test.com",
            "test1234"
        )
        signUpPort.signUp(signUpRequest)

        Assertions.assertThrows(AlreadyRegisteredEmailException::class.java) { signUpPort.signUp(signUpRequest) }
    }

}