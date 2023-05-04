package com.companimal.application.member

import com.companimal.application.common.dto.Response
import com.companimal.application.member.dto.MemberResponse
import com.companimal.domain.member.port.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/member")
@RestController
class MemberController(
    private val getMemberUseCase: GetMemberUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
) {

    @GetMapping("/{id}")
    fun getMemberById(
        @PathVariable(name = "id", required = true) id: Long
    ): ResponseEntity<Response> {
        return ResponseEntity.ok(Response.ok(MemberResponse.of(getMemberUseCase.get(GetMemberRequest(id)))))
    }

    @PostMapping
    fun signUp(
        @Valid @RequestBody signUpRequest: SignUpRequest
    ): ResponseEntity<Void> {
        signUpUseCase.signUp(signUpRequest)
        return ResponseEntity.ok().build()
    }

    @PutMapping
    fun updatePassword(
        @Valid @RequestBody changePasswordRequest: ChangePasswordRequest
    ): ResponseEntity<Void> {
        changePasswordUseCase.changePassword(changePasswordRequest)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteMember(
        @PathVariable(name = "id", required = true) id: Long
    ): ResponseEntity<Void> {
        deleteMemberUseCase.delete(DeleteMemberRequest(id))
        return ResponseEntity.ok().build()
    }
}