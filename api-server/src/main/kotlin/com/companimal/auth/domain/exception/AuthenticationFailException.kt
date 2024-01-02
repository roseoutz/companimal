package com.companimal.auth.domain.exception

import com.companimal.common.domain.constant.ErrorCode
import com.companimal.common.domain.exception.CompanimalException

open class AuthenticationFailException(val memberId: Long? = null, errorCode: ErrorCode, throwable: Throwable? = null):
    CompanimalException(errorCode = errorCode, throwable = throwable) {
}