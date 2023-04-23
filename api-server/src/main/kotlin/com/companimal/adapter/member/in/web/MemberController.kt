package com.companimal.adapter.member.`in`.web

import com.companimal.adapter.member.`in`.web.dto.MemberResponse
import com.companimal.application.member.port.`in`.MemberPort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/member")
@RestController
class MemberController(
    private val memberPort: MemberPort
) {

    @GetMapping("/{id}")
    fun getMemberById(
        @PathVariable(name = "id", required = true) id: Long
    ): ResponseEntity<MemberResponse> {
        val member = memberPort.getMember(id)
        return ResponseEntity.ok(MemberResponse.of(member))
    }
}