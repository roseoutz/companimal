package com.companimal.domain.member.constants

import com.companimal.domain.common.constant.ErrorCode

enum class MemberErrorCode(private val errorMessage: String): ErrorCode {
    NO_SUCH_MEMBER("등록된 사용자가 존재하지 않습니다."),
    ALREADY_REGISTER_EMAIL("해당 이메일로 등록된 사용자가 이미 존재합니다."),
    INVALID_FORMAT_PASSWORD("비밀번호 정책에 부합하지 않은 비밀번호 입니다."),
    INVALID_PASSWORD("비밀번호가 옳바르지 않습니다."),
    SAME_PASSWORD("기존 비밀번호와 신규 비밀번호는 동일할 수 없습니다."),
    ;

    override fun getErrorCode(): String {
        return this.name
    }

    override fun getErrorMessage(): String {
        return this.errorMessage
    }
}