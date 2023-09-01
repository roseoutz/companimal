package com.companimal.kms.presentation.web

import com.companimal.kms.application.dto.ServerKeyResponse
import com.companimal.kms.domain.port.GetServerKeyPort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kms")
class PublicKeyController(
    private val getServerKeyPort: GetServerKeyPort
) {

    @GetMapping("/sign/key")
    fun getSigningPublicKey(): ResponseEntity<ServerKeyResponse> =
        ResponseEntity.ok(
            ServerKeyResponse.of(
                getServerKeyPort.getServerKey()
            )
        )
}