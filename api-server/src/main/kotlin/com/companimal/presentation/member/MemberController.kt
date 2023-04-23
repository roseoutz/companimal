package com.companimal.presentation.member

import com.companimal.application.member.adapter.MemberAdapter
import com.companimal.presentation.member.dto.MemberResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/member")
@RestController
class MemberController(
    private val memberAdapter: MemberAdapter
) {

    @GetMapping("/{id}")
    fun getMemberById(
        @PathVariable(name = "id", required = true) id: Long
    ): ResponseEntity<MemberResponse> {
        val member = memberAdapter.getMember(id)
        return ResponseEntity.ok(MemberResponse.of(member))
    }
}