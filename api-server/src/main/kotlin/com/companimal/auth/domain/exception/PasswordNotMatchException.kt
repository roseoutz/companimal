package com.companimal.auth.domain.exception

import com.companimal.auth.domain.constants.SignInErrorCode

class PasswordNotMatchException(memberId: Long? = null): AuthenticationFailException(memberId = memberId, errorCode = SignInErrorCode.PASSWORD_NOT_MATCH) {
}