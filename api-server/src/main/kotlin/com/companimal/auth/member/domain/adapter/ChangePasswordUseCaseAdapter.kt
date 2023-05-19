package com.companimal.auth.member.domain.adapter

import com.companimal.common.domain.validation.PasswordValidator
import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.auth.member.domain.exception.InvalidFormatPasswordException
import com.companimal.auth.member.domain.exception.NoSuchMemberException
import com.companimal.auth.member.domain.exception.PasswordInvalidException
import com.companimal.auth.member.domain.exception.CannotUseSamePasswordException
import com.companimal.auth.member.domain.persistence.MemberReader
import com.companimal.auth.member.domain.persistence.MemberWriter
import com.companimal.auth.member.domain.port.ChangePasswordRequest
import com.companimal.auth.member.domain.port.ChangePasswordUseCasePort
import org.springframework.stereotype.Service

@Service
class ChangePasswordUseCaseAdapter(
    private val memberReader: MemberReader,
    private val memberWriter: MemberWriter,
    private val hashEncoderPort: HashEncoderPort,
): ChangePasswordUseCasePort {

    override fun changePassword(changePasswordRequest: ChangePasswordRequest) {

        checkVerifyPassword(changePasswordRequest)
        checkPasswordFormat(changePasswordRequest)

        val salt = hashEncoderPort.getSaltValue()
        val encoded = hashEncoderPort.encode(changePasswordRequest.newPassword)
        memberWriter.updatePassword(changePasswordRequest.id, encoded, salt)
    }

    private fun checkVerifyPassword(changePasswordRequest: ChangePasswordRequest) {
        val member = memberReader.findById(changePasswordRequest.id) ?: throw NoSuchMemberException()

        if (!hashEncoderPort.match(changePasswordRequest.oldPassword, member.salt, member.password!!)) {
            throw PasswordInvalidException()
        }
    }

    private fun checkPasswordFormat(changePasswordRequest: ChangePasswordRequest) {
        if (changePasswordRequest.newPassword == changePasswordRequest.oldPassword) {
            throw CannotUseSamePasswordException()
        }

        if (!PasswordValidator.validate(changePasswordRequest.newPassword)) {
            throw InvalidFormatPasswordException()
        }
    }

}
