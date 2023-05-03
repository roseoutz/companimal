package com.companimal.domain.member.usecase

import com.companimal.domain.crypto.service.HashEncoderService
import com.companimal.domain.member.exception.PasswordPolicyValidateException
import com.companimal.domain.member.persistence.MemberStore
import org.springframework.stereotype.Service

@Service
class ChangePasswordUseCaseImpl(
    private val memberStore: MemberStore,
    private val hashEncoderService: HashEncoderService,
): ChangePasswordUseCase {
    override fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        if (changePasswordRequest.newPassword == changePasswordRequest.oldPassword) {
            throw PasswordPolicyValidateException()
        }

        val salt = hashEncoderService.getSaltValue()
        val encoded = hashEncoderService.encode(changePasswordRequest.newPassword)
        memberStore.updatePassword(changePasswordRequest.id, encoded, salt)
    }

}
