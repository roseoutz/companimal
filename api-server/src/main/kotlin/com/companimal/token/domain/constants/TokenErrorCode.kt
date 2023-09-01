package com.companimal.token.domain.constants

import com.companimal.common.domain.constant.ErrorCode

enum class TokenErrorCode(private val errorMessage: String): ErrorCode {
    SERVER_KEY_NOT_EXIST("서버키가 존재하지 않습니다."),
    SERVER_KEY_NOT_MATCH("서버키가 일치하지 않습니다."),
    ALREADY_EXPIRED_TOKEN("만료된 토큰입니다."),
    ;
    override fun getErrorCode(): String = this.name

    override fun getErrorMessage(): String = this.errorMessage

    override fun getErrorMessage(param: String?): String = this.errorMessage.plus(param)
}