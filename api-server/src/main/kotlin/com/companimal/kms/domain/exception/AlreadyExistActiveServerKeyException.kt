package com.companimal.kms.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.kms.domain.constants.ServerKeyErrorCode

class AlreadyExistActiveServerKeyException: CompanimalException(errorCode = ServerKeyErrorCode.ALREADY_EXIST_ACTIVE_SERVER_KEY) {
}