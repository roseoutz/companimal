package com.companimal.signIn.domain.constants

import com.companimal.common.domain.constant.ErrorCode

enum class SignInErrorCode(private val errorMessage: String): ErrorCode {
    ALREADY_REGISTER_MEMBER("해당 이메일로 등록된 사용자가 이미 존재합니다."),
    ;

    override fun getErrorCode(): String = this.name

    override fun getErrorMessage(): String = this.errorMessage

    override fun getErrorMessage(param: String?): String = this.errorMessage.plus(param)
}