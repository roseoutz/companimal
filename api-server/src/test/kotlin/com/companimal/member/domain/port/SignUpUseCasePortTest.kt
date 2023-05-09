package com.companimal.member.domain.port

import com.companimal.member.domain.exception.AlreadyRegisteredEmailException
import com.companimal.member.infrastructure.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class SignUpUseCasePortTest @Autowired constructor(
    private val signUpUseCasePort: SignUpUseCasePort,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should signup member`() {
        val signUpRequest = SignUpRequest(
            "test@test.com",
            "test1234"
        )
        signUpUseCasePort.signUp(signUpRequest)

        val entity = memberRepository.findByEmail(signUpRequest.email)

        Assertions.assertNotNull(entity)
    }

    @Test
    fun `should throw alreadyRegisteredEmail Exception`() {
        val signUpRequest = SignUpRequest(
            "test@test.com",
            "test1234"
        )
        signUpUseCasePort.signUp(signUpRequest)

        Assertions.assertThrows(AlreadyRegisteredEmailException::class.java) { signUpUseCasePort.signUp(signUpRequest) }
    }

}