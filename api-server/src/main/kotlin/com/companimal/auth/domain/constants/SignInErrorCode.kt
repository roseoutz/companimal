package com.companimal.auth.domain.constants

import com.companimal.common.domain.constant.ErrorCode

enum class SignInErrorCode(private val errorMessage: String): ErrorCode {
    AUTHENTICATION_ERROR("로그인 중 오류가 발생하였습니다."),
    PASSWORD_NOT_MATCH("인증정보가 옳바르지 않습니다."),
    MEMBER_NOT_EXIST("존재하지 않은 사용자입니다."),
    MEMBER_STATUS_NOT_ACTIVE("로그인 할 수 없는 사용자입니다."),
    MEMBER_NOT_CONFIRMED("이메일 인증이 완료되지 않은 사용자입니다."),

    ERROR_DURING_TOKEN_CREATION("로그인 과정 중 오류가 발생하였습니다."),
    ;

    override fun getErrorCode(): String = this.name

    override fun getErrorMessage(): String = this.errorMessage

    override fun getErrorMessage(param: String?): String = this.errorMessage.plus(param)
}