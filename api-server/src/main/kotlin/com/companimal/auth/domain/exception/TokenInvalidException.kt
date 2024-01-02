package com.companimal.auth.domain.exception

import com.companimal.auth.domain.constants.SignInErrorCode
import com.companimal.common.domain.exception.CompanimalException

class TokenInvalidException: CompanimalException(errorCode = SignInErrorCode.TOKEN_INVALID) {
}