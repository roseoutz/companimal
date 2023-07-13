package com.companimal.kms.domain.constants

import com.companimal.common.domain.constant.ErrorCode

enum class ServerKeyErrorCode(private val errorMessage: String): ErrorCode {
    ACTIVE_SERVER_KEY_NOT_EXIST("활성화된 서버키가 존재하지 않습니다."),
    ALREADY_EXIST_ACTIVE_SERVER_KEY("이미 활성화된 서버키가 존재합니다."),
    ;
    override fun getErrorCode(): String = this.name

    override fun getErrorMessage(): String = this.errorMessage

    override fun getErrorMessage(param: String?): String = this.errorMessage.plus(param)
}