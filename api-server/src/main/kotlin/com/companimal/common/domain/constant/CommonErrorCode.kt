package com.companimal.common.domain.constant

enum class CommonErrorCode constructor(private val errorMessage: String): ErrorCode {
    COMMON_ERROR("요청 처리 중 오류가 발생하였습니다."),
    REQUEST_PARAMETER_IS_NULL("해당 값은 필수 입력 값입니다. ")
    ;

    override fun getErrorCode(): String = this.name

    override fun getErrorMessage(): String = this.errorMessage

    override fun getErrorMessage(param: String?): String = this.errorMessage.plus(param)

}