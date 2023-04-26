package com.companimal.presentation.member

import com.companimal.application.MemberFacade
import com.companimal.presentation.member.dto.MemberResponse
import com.companimal.presentation.member.dto.SignUpRequest
import com.companimal.domain.member.constants.MemberStatus
import com.companimal.domain.member.service.MemberService
import com.companimal.domain.member.dto.Member
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/member")
@RestController
class MemberController(
    private val memberFacade: MemberFacade,
) {

    @GetMapping("/{id}")
    fun getMemberById(
        @PathVariable(name = "id", required = true) id: Long
    ): ResponseEntity<MemberResponse> {
        return ResponseEntity.ok(memberFacade.getMemberById(id))
    }

    @PostMapping
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Void> {
        memberFacade.signUp(signUpRequest)
        return ResponseEntity.ok().build();
    }
}