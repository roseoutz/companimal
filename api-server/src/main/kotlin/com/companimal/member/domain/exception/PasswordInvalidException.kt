package com.companimal.auth.member.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.auth.member.domain.constants.MemberErrorCode

class PasswordInvalidException: CompanimalException(errorCode = MemberErrorCode.INVALID_PASSWORD) {
}