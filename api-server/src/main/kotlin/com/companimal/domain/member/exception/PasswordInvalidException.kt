package com.companimal.domain.member.exception

import com.companimal.domain.common.exception.CompanimalException
import com.companimal.domain.member.constants.MemberErrorCode

class PasswordInvalidException: CompanimalException(errorCode = MemberErrorCode.INVALID_PASSWORD) {
}