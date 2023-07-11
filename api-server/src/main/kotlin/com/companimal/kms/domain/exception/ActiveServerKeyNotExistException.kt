package com.companimal.kms.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.kms.domain.constants.ServerKeyErrorCode

class ActiveServerKeyNotExistException: CompanimalException(errorCode = ServerKeyErrorCode.ACTIVE_SERVER_KEY_NOT_EXIST) {
}