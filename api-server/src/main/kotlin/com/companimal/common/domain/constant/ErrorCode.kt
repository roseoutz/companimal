package com.companimal.common.domain.constant

interface ErrorCode {

    fun getErrorCode(): String

    fun getErrorMessage(): String

    fun getErrorMessage(param: String?): String

}