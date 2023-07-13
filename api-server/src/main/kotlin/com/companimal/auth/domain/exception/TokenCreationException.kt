package com.companimal.auth.domain.exception

import com.companimal.auth.domain.constants.SignInErrorCode
import com.companimal.common.domain.exception.CompanimalException

class TokenCreationException(): CompanimalException(errorCode = SignInErrorCode.ERROR_DURING_TOKEN_CREATION) {
}