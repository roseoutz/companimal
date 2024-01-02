package com.companimal.auth.domain.exception

import com.companimal.common.domain.constant.ErrorCode

class CannotSignInMemberException(memberId: Long? = null, errorCode: ErrorCode): AuthenticationFailException(memberId = memberId, errorCode = errorCode) {
}