package com.companimal.auth.member.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.auth.member.domain.constants.MemberErrorCode

class NoSuchMemberException: CompanimalException(errorCode = MemberErrorCode.NO_SUCH_MEMBER) {
}