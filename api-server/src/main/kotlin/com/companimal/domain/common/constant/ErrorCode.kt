package com.companimal.domain.common.constant

interface ErrorCode {

    fun getErrorCode(): String

    fun getErrorMessage(): String

    fun getErrorMessage(param: String?): String

}