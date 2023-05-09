package com.companimal.common.application.dto

import com.companimal.common.domain.exception.CompanimalException

data class ResponseDTO<T>(
    val data: T? = null,
    val success: Boolean? = true,
    val errorCode: String? = null,
    val errorMessage: String? = null
) {
    companion object {

        fun <T> error(companimalException: CompanimalException): ResponseDTO<T> = ResponseDTO(
            success = false,
            errorCode = companimalException.getErrorCode(),
            errorMessage = companimalException.getErrorMessage()
        )

        fun <T> ok(data: T? = null) = ResponseDTO(
            data = data
        )
    }
}