package com.companimal.domain.common.exception

import com.companimal.domain.common.constant.ErrorCode

open class CompanimalException(
    val errorCode: ErrorCode,
    val throwable: Throwable? = null,
    val param: String? = null,
): RuntimeException(
    errorCode.getErrorMessage(),
    throwable
) {

    fun getErrorCode(): String = this.errorCode.toString()

    fun getErrorMessage(): String {
        return if (param != null) {
            this.errorCode.getErrorMessage(param)
        } else {
            this.errorCode.getErrorMessage()
        }
    }

    fun getDetailErrorMessage(): String? = cause?.message
}