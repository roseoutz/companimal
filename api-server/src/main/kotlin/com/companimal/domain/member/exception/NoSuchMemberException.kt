package com.companimal.domain.member.exception

import com.companimal.domain.common.exception.CompanimalException
import com.companimal.domain.member.constants.MemberErrorCode

class NoSuchMemberException: CompanimalException(errorCode = MemberErrorCode.NO_SUCH_MEMBER) {
}