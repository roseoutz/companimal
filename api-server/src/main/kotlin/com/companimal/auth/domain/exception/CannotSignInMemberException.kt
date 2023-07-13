package com.companimal.auth.domain.exception

import com.companimal.common.domain.constant.ErrorCode
import com.companimal.common.domain.exception.CompanimalException

class CannotSignInMemberException(errorCode: ErrorCode): CompanimalException(errorCode = errorCode) {
}