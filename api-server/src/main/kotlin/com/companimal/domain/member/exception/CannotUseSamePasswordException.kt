package com.companimal.domain.member.exception

import com.companimal.domain.common.exception.CompanimalException
import com.companimal.domain.member.constants.MemberErrorCode

class CannotUseSamePasswordException: CompanimalException(errorCode = MemberErrorCode.SAME_PASSWORD) {
}