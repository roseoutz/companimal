package com.companimal.domain.member.adapter

import com.companimal.domain.crypto.service.HashEncoderService
import com.companimal.domain.member.exception.InvalidFormatPasswordException
import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.exception.PasswordInvalidException
import com.companimal.domain.member.exception.CannotUseSamePasswordException
import com.companimal.domain.member.persistence.MemberReader
import com.companimal.domain.member.persistence.MemberStore
import com.companimal.domain.member.port.ChangePasswordRequest
import com.companimal.domain.member.port.ChangePasswordUseCase
import org.springframework.stereotype.Service

@Service
class ChangePasswordUseCaseImpl(
    private val memberReader: MemberReader,
    private val memberStore: MemberStore,
    private val hashEncoderService: HashEncoderService,
): ChangePasswordUseCase {

    override fun changePassword(changePasswordRequest: ChangePasswordRequest) {

        checkVerifyPassword(changePasswordRequest)
        checkPasswordFormat(changePasswordRequest)

        val salt = hashEncoderService.getSaltValue()
        val encoded = hashEncoderService.encode(changePasswordRequest.newPassword)
        memberStore.updatePassword(changePasswordRequest.id, encoded, salt)
    }

    private fun checkVerifyPassword(changePasswordRequest: ChangePasswordRequest) {
        val member = memberReader.findByIdOrNull(changePasswordRequest.id) ?: throw NoSuchMemberException()

        if (!hashEncoderService.match(changePasswordRequest.oldPassword, member.salt, member.password)) {
            throw PasswordInvalidException()
        }
    }

    private fun checkPasswordFormat(changePasswordRequest: ChangePasswordRequest) {
        if (changePasswordRequest.newPassword == changePasswordRequest.oldPassword) {
            throw CannotUseSamePasswordException()
        }

        if (!changePasswordRequest.newPassword.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"))) {
            throw InvalidFormatPasswordException()
        }
    }

}
