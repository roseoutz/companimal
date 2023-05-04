package com.companimal.domain.common.constant

enum class CommonErrorCode constructor(private val errorMessage: String): ErrorCode {
    COMMON_ERROR(""),
    NOT_HANDLE_EXCEPTION(""),
    ;

    override fun getErrorCode(): String {
        return this.name
    }

    override fun getErrorMessage(): String {
        return this.errorMessage
    }
}