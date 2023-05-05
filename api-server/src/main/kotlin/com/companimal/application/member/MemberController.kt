package com.companimal.application.member

import com.companimal.application.common.dto.ResponseDTO
import com.companimal.application.member.dto.MemberResponse
import com.companimal.domain.member.port.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "MemberController", description = "사용자 API")
@RequestMapping("/v1/member")
@RestController
class MemberController(
    private val getMemberUseCase: GetMemberUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
) {

    @Operation(summary = "사용자 조회", description = "사용자의 id 값을 이용하여 사용자의 정보를 조회한다.")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200", description = "사용자 정보 조회 성공"
        ),
        ApiResponse(
            responseCode = "400", description = "사용자 정보 조회 실패"
        )
    ])
    @GetMapping("/{id}")
    fun getMemberById(
        @Parameter(description = "사용자의 ID 값")
        @PathVariable(name = "id", required = true)
        id: Long
    ): ResponseEntity<ResponseDTO<MemberResponse>> {
        return ResponseEntity.ok(ResponseDTO.ok(MemberResponse.of(getMemberUseCase.get(id))))
    }

    @Operation(summary = "회원가입", description = "email 정보를 이용하여 회원가입을 진행한다.")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200", description = "사용자 회원 가입 성공"
        ),
        ApiResponse(
            responseCode = "400", description = "사용자 회원 가입 실패"
        )
    ])
    @PostMapping
    fun signUp(
        @Valid
        @RequestBody
        signUpRequest: SignUpRequest
    ): ResponseEntity<ResponseDTO<Any>> {
        signUpUseCase.signUp(signUpRequest)
        return ResponseEntity.ok(ResponseDTO.ok())
    }

    @Operation(summary = "비밀번호 변경", description = "새로운 비밀번호로 변경을 한다.")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200", description = "비밀번호 변경 성공"
        ),
        ApiResponse(
            responseCode = "400", description = "비밀번호 변경 실패"
        )
    ])
    @PutMapping
    fun updatePassword(
        @Valid
        @RequestBody
        changePasswordRequest: ChangePasswordRequest
    ): ResponseEntity<ResponseDTO<Any>> {
        changePasswordUseCase.changePassword(changePasswordRequest)
        return ResponseEntity.ok(ResponseDTO.ok())
    }

    @Operation(summary = "사용자 비활성화", description = "사용자의 상태를 비활성화 상태로 전환한다.")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200", description = "사용자 비활성화 성공"
        ),
        ApiResponse(
            responseCode = "400", description = "사용자 비활성화 실패"
        )
    ])
    @DeleteMapping("/{id}")
    fun deleteMember(
        @Parameter(description = "사용자의 ID 값")
        @PathVariable(name = "id", required = true)
        id: Long
    ): ResponseEntity<ResponseDTO<Any>> {
        deleteMemberUseCase.delete(id)
        return ResponseEntity.ok(ResponseDTO.ok())
    }
}