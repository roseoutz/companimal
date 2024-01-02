package com.companimal.auth.domain.exception

import com.companimal.auth.domain.constants.SignInErrorCode

class MemberNotExistException: AuthenticationFailException(errorCode = SignInErrorCode.MEMBER_NOT_EXIST)