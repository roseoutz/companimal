package com.companimal.domain.member.exception

import com.companimal.domain.common.exception.CompanimalException
import com.companimal.domain.member.constants.MemberErrorCode

class InvalidFormatEmailException: CompanimalException(errorCode = MemberErrorCode.INVALID_FORMAT_EMAIL) {
}