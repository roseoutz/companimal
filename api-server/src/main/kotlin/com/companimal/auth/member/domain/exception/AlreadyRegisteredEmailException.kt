package com.companimal.auth.member.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.auth.member.domain.constants.MemberErrorCode

class AlreadyRegisteredEmailException: CompanimalException(errorCode = MemberErrorCode.ALREADY_REGISTER_EMAIL) {
}