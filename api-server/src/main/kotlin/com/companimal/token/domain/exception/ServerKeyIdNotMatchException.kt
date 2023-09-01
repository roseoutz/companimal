package com.companimal.token.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.token.domain.constants.TokenErrorCode

class ServerKeyIdNotMatchException(serverKeyId: String): CompanimalException(errorCode = TokenErrorCode.SERVER_KEY_NOT_EXIST) {
}