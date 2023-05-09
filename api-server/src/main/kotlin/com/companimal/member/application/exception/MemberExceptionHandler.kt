package com.companimal.member.application.exception

import com.companimal.common.application.dto.ResponseDTO
import com.companimal.common.application.exception.BaseExceptionHandler
import com.companimal.common.domain.exception.CompanimalException
import com.companimal.member.domain.exception.AlreadyRegisteredEmailException
import com.companimal.member.domain.exception.InvalidFormatPasswordException
import com.companimal.member.domain.exception.NoSuchMemberException
import com.companimal.member.domain.exception.PasswordInvalidException
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(0)
@RestControllerAdvice
class MemberExceptionHandler: BaseExceptionHandler() {

    @ExceptionHandler(NoSuchMemberException::class)
    fun noSuchMemberExceptionHandler(noSuchMemberException: NoSuchMemberException): ResponseEntity<ResponseDTO<Any>> = response(noSuchMemberException)

    @ExceptionHandler(PasswordInvalidException::class)
    fun passwordInvalidExceptionHandler(passwordInvalidException: PasswordInvalidException): ResponseEntity<ResponseDTO<Any>> = response(passwordInvalidException)

    @ExceptionHandler(AlreadyRegisteredEmailException::class)
    fun alreadyRegisteredEmailExceptionHandler(alreadyRegisteredEmailException: AlreadyRegisteredEmailException): ResponseEntity<ResponseDTO<Any>> = response(alreadyRegisteredEmailException)

    @ExceptionHandler(InvalidFormatPasswordException::class)
    fun invalidFormatPasswordExceptionHandler(invalidFormatPasswordException: InvalidFormatPasswordException): ResponseEntity<ResponseDTO<Any>> = response(invalidFormatPasswordException)

    @ExceptionHandler(CompanimalException::class)
    fun companimalExceptionHandler(companimalException: CompanimalException): ResponseEntity<ResponseDTO<Any>> {
        logger.error("not handled exception occur : {}", companimalException.message, companimalException)
        return response(companimalException)
    }


}