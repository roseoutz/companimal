package com.companimal.application.common.exception

import com.companimal.application.common.dto.Response
import com.companimal.domain.common.constant.CommonErrorCode
import com.companimal.domain.common.exception.CompanimalException
import com.companimal.infrastructure.common.logger.Logger
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(99)
@RestControllerAdvice
class BaseExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(exception: Exception): ResponseEntity<Response> {
        logger.error("not handled exception occur : {}", exception.message, exception)

        return response(CompanimalException(CommonErrorCode.COMMON_ERROR, exception))
    }

    protected fun response(companimalException: CompanimalException): ResponseEntity<Response> {
        return ResponseEntity.badRequest().body(Response.error(companimalException))
    }
    companion object {
        val logger = Logger<BaseExceptionHandler>()
    }
}