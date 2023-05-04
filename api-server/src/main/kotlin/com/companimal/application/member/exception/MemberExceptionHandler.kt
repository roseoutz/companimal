package com.companimal.application.member.exception

import com.companimal.application.common.dto.Response
import com.companimal.application.common.exception.BaseExceptionHandler
import com.companimal.domain.common.exception.CompanimalException
import com.companimal.domain.member.exception.AlreadyRegisteredEmailException
import com.companimal.domain.member.exception.InvalidFormatPasswordException
import com.companimal.domain.member.exception.NoSuchMemberException
import com.companimal.domain.member.exception.PasswordInvalidException
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(0)
@RestControllerAdvice
class MemberExceptionHandler: BaseExceptionHandler() {

    @ExceptionHandler(NoSuchMemberException::class)
    fun noSuchMemberExceptionHandler(noSuchMemberException: NoSuchMemberException): ResponseEntity<Response> = response(noSuchMemberException)

    @ExceptionHandler(PasswordInvalidException::class)
    fun passwordInvalidExceptionHandler(passwordInvalidException: PasswordInvalidException): ResponseEntity<Response> = response(passwordInvalidException)

    @ExceptionHandler(AlreadyRegisteredEmailException::class)
    fun alreadyRegisteredEmailExceptionHandler(alreadyRegisteredEmailException: AlreadyRegisteredEmailException): ResponseEntity<Response> = response(alreadyRegisteredEmailException)

    @ExceptionHandler(InvalidFormatPasswordException::class)
    fun invalidFormatPasswordExceptionHandler(invalidFormatPasswordException: InvalidFormatPasswordException): ResponseEntity<Response> = response(invalidFormatPasswordException)

    @ExceptionHandler(CompanimalException::class)
    fun companimalExceptionHandler(companimalException: CompanimalException): ResponseEntity<Response> {
        logger.error("not handled exception occur : {}", companimalException.message, companimalException)
        return response(companimalException)
    }


}