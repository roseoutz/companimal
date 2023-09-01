package com.companimal.token.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.token.domain.constants.TokenErrorCode

class AlreadyExpiredTokenException(): CompanimalException(errorCode = TokenErrorCode.ALREADY_EXPIRED_TOKEN) {
}