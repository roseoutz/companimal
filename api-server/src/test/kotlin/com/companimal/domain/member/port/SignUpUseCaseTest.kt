package com.companimal.domain.member.port

import com.companimal.domain.member.exception.AlreadyRegisteredEmailException
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class SignUpUseCaseTest @Autowired constructor(
    private val signUpUseCase: SignUpUseCase,
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `should signup member`() {
        val signUpRequest = SignUpRequest(
            "test@test.com",
            "test1234"
        )
        signUpUseCase.signUp(signUpRequest)

        val entity = memberRepository.findByEmail(signUpRequest.email)

        Assertions.assertNotNull(entity)
    }

    @Test
    fun `should throw alreadyRegisteredEmail Exception`() {
        val signUpRequest = SignUpRequest(
            "test@test.com",
            "test1234"
        )
        signUpUseCase.signUp(signUpRequest)

        Assertions.assertThrows(AlreadyRegisteredEmailException::class.java) { signUpUseCase.signUp(signUpRequest) }
    }

}