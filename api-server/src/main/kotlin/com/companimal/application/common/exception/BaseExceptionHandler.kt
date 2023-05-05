package com.companimal.application.common.exception

import com.companimal.application.common.dto.ResponseDTO
import com.companimal.domain.common.constant.CommonErrorCode
import com.companimal.domain.common.exception.CompanimalException
import com.companimal.infrastructure.common.logger.Logger
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(99)
@RestControllerAdvice
class BaseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationExceptionHandler(methodArgumentNotValidException: MethodArgumentNotValidException): ResponseEntity<ResponseDTO<Any>> {
        val field = methodArgumentNotValidException.bindingResult.allErrors.first().arguments?.first() as DefaultMessageSourceResolvable
        val type = field.defaultMessage
        return response(CompanimalException(
            errorCode = CommonErrorCode.REQUEST_PARAMETER_IS_NULL,
            param = type,
            throwable = methodArgumentNotValidException)
        )
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(exception: Exception): ResponseEntity<ResponseDTO<Any>> {
        logger.error("not handled exception occur : {}", exception.message, exception)

        return response(CompanimalException(errorCode = CommonErrorCode.COMMON_ERROR, throwable = exception))
    }

    protected fun response(companimalException: CompanimalException): ResponseEntity<ResponseDTO<Any>> {
        return ResponseEntity.badRequest().body(ResponseDTO.error(companimalException))
    }
    companion object {
        val logger = Logger<BaseExceptionHandler>()
    }
}