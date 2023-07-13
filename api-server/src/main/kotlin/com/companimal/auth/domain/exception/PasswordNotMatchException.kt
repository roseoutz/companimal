package com.companimal.auth.domain.exception

import com.companimal.auth.domain.constants.SignInErrorCode
import com.companimal.common.domain.exception.CompanimalException

class PasswordNotMatchException: CompanimalException(SignInErrorCode.PASSWORD_NOT_MATCH) {
}