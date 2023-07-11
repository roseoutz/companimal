package com.companimal.member.domain.exception

import com.companimal.common.domain.exception.CompanimalException
import com.companimal.member.domain.constants.MemberErrorCode

class NoSuchMemberException: CompanimalException(errorCode = MemberErrorCode.NO_SUCH_MEMBER) {
}