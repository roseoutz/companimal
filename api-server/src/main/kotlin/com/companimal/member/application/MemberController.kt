package com.companimal.member.application

import com.companimal.member.application.dto.MemberResponse
import com.companimal.common.application.dto.ResponseDTO
import com.companimal.member.domain.port.*
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
    private val getMemberPort: GetMemberPort,
    private val createMemberPort: CreateMemberPort,
    private val deleteMemberPort: DeleteMemberPort,
    private val changePasswordPort: ChangePasswordPort,
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
        return ResponseEntity.ok(ResponseDTO.ok(MemberResponse.of(getMemberPort.get(id))))
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
        createMemberRequest: CreateMemberRequest
    ): ResponseEntity<ResponseDTO<Unit>> {
        createMemberPort.signUp(createMemberRequest)
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
    ): ResponseEntity<ResponseDTO<Unit>> {
        changePasswordPort.changePassword(changePasswordRequest)
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
        deleteMemberPort.delete(id)
        return ResponseEntity.ok(ResponseDTO.ok())
    }
}