package com.companimal.auth.member.domain.port

import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.auth.member.domain.exception.CannotUseSamePasswordException
import com.companimal.auth.member.domain.exception.InvalidFormatPasswordException
import com.companimal.auth.member.domain.exception.PasswordInvalidException
import com.companimal.auth.member.infrastructure.persistence.MemberEntity
import com.companimal.auth.member.infrastructure.persistence.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class ChangePasswordPortTest @Autowired constructor(
    private val changePasswordUsecasePort: ChangePasswordPort,
    private val memberRepository: MemberRepository,
    private val hashEncoderPort: HashEncoderPort,
) {

    @Test
    fun `should change password`() {
        val salt = hashEncoderPort.getSaltValue()
        val encodedPassword = hashEncoderPort.encode("test1234$salt")
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
        changePasswordUsecasePort.changePassword(changePasswordRequest)


        val entity = memberRepository.findByIdOrNull(savedEntity.id!!) ?: Assertions.fail()
        Assertions.assertNotEquals(encodedPassword, entity.password)
    }

    @Test
    fun `should cannot use same password`() {
        val salt = hashEncoderPort.getSaltValue()
        val encodedPassword = hashEncoderPort.encode("test1234$salt")
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

        Assertions.assertThrows(CannotUseSamePasswordException::class.java) {
            changePasswordUsecasePort.changePassword(
                changePasswordRequest
            )
        }
    }

    @Test
    fun `should fail not match old password`() {
        val salt = hashEncoderPort.getSaltValue()
        val encodedPassword = hashEncoderPort.encode("test1234$salt")
        val memberEntity = MemberEntity(
            email = "test@test.com",
            password = encodedPassword,
            salt = salt
        )
        val savedEntity = memberRepository.save(memberEntity)


        val changePasswordRequest = ChangePasswordRequest(
            id = savedEntity.id!!,
            oldPassword = "test",
            newPassword = "test1234"
        )

        Assertions.assertThrows(PasswordInvalidException::class.java) {
            changePasswordUsecasePort.changePassword(
                changePasswordRequest
            )
        }
    }

    @Test
    fun `should fail invalid password format`() {
        val salt = hashEncoderPort.getSaltValue()
        val encodedPassword = hashEncoderPort.encode("test1234$salt")
        val memberEntity = MemberEntity(
            email = "test@test.com",
            password = encodedPassword,
            salt = salt
        )
        val savedEntity = memberRepository.save(memberEntity)


        val changePasswordRequest = ChangePasswordRequest(
            id = savedEntity.id!!,
            oldPassword = "test1234",
            newPassword = "testtest"
        )

        Assertions.assertThrows(InvalidFormatPasswordException::class.java) {
            changePasswordUsecasePort.changePassword(
                changePasswordRequest
            )
        }
    }

}