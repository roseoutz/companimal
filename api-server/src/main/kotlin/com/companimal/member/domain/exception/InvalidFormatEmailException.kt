package com.companimal.member.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.member.domain.constants.MemberErrorCode

class InvalidFormatEmailException: CompanimalException(errorCode = MemberErrorCode.INVALID_FORMAT_EMAIL) {
}