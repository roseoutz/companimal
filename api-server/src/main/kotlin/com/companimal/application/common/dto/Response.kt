package com.companimal.application.common.dto

import com.companimal.domain.common.exception.CompanimalException

data class Response(
    val data: Any? = null,
    val success: Boolean? = true,
    val errorCode: String? = null,
    val errorMessage: String? = null
) {
    companion object {

        fun error(companimalException: CompanimalException): Response = Response(
            success = false,
            errorCode = companimalException.getErrorCode(),
            errorMessage = companimalException.getErrorMessage()
        )

        fun ok(data: Any) = Response(
            data = data
        )
    }
}