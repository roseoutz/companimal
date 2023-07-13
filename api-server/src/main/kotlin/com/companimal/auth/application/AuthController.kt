package com.companimal.auth.application

import com.companimal.auth.domain.port.SignInPort
import com.companimal.auth.domain.port.SignInRequest
import com.companimal.auth.domain.port.SignInResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth/")
class AuthController(
    private val signInPort: SignInPort
) {

    @PostMapping
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ): ResponseEntity<SignInResponse> {
        return ResponseEntity.ok(
            signInPort.signIn(signInRequest)
        )
    }
}