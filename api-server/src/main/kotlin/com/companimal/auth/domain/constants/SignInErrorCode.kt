package com.companimal.auth.domain.constants

import com.companimal.common.domain.constant.ErrorCode

enum class SignInErrorCode(private val errorMessage: String): ErrorCode {
    ;

    override fun getErrorCode(): String = this.name

    override fun getErrorMessage(): String = this.errorMessage

    override fun getErrorMessage(param: String?): String = this.errorMessage.plus(param)
}