package com.companimal.domain.common.exception

import com.companimal.domain.common.constant.ErrorCode

open class CompanimalException(
    val errorCode: ErrorCode,
    val throwable: Throwable? = null,
): RuntimeException(
    errorCode.getErrorMessage(),
    throwable
) {

    fun getErrorCode(): String = this.errorCode.toString()

    fun getErrorMessage(): String = this.errorCode.getErrorMessage()

    fun getDetailErrorMessage(): String? = cause?.message
}