package com.companimal.signIn.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.member.domain.constants.MemberErrorCode

class AlreadyRegisteredMemberException: CompanimalException(errorCode = MemberErrorCode.ALREADY_REGISTER_EMAIL) {
}