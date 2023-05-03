package com.companimal.domain.member.usecase

import com.companimal.domain.crypto.service.HashEncoderService
import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.exception.PasswordPolicyValidateException
import com.companimal.infrastructure.member.persistence.MemberEntity
import com.companimal.infrastructure.member.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class ChangePasswordUseCaseTest @Autowired constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val memberRepository: MemberRepository,
    private val hashEncoderService: HashEncoderService,
) {

    @Test
    fun `should change password`() {
        val salt = hashEncoderService.getSaltValue()
        val encodedPassword = hashEncoderService.encode("test1234$salt")
        val memberEntity = MemberEntity(
            email = "test@test.com",
            password = encodedPassword,
            salt = salt
        )
        val savedEntity = memberRepository.save(memberEntity)


        val changePasswordRequest = ChangePasswordRequest(
            id = savedEntity.id!!,
            oldPassword = "test1234",
            newPassword = "test1234!!"
        )
        changePasswordUseCase.changePassword(changePasswordRequest)


        val entity = memberRepository.findByIdOrNull(savedEntity.id!!) ?: Assertions.fail()
        Assertions.assertNotEquals(encodedPassword, entity.password)
    }

    @Test
    fun `should fail change password`() {
        val salt = hashEncoderService.getSaltValue()
        val encodedPassword = hashEncoderService.encode("test1234$salt")
        val memberEntity = MemberEntity(
            email = "test@test.com",
            password = encodedPassword,
            salt = salt
        )
        val savedEntity = memberRepository.save(memberEntity)


        val changePasswordRequest = ChangePasswordRequest(
            id = savedEntity.id!!,
            oldPassword = "test1234",
            newPassword = "test1234"
        )

        Assertions.assertThrows(PasswordPolicyValidateException::class.java) {
            changePasswordUseCase.changePassword(
                changePasswordRequest
            )
        }
    }

}