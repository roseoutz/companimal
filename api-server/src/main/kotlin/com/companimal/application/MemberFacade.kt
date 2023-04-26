package com.companimal.application

import com.companimal.domain.member.command.MemberCommand
import com.companimal.domain.member.service.MemberService
import com.companimal.presentation.member.dto.MemberResponse
import com.companimal.presentation.member.dto.SignUpRequest
import org.springframework.stereotype.Service

@Service
class MemberFacade(
    private val memberService: MemberService,
) {

    fun getMemberById(id: Long): MemberResponse {
        val member = memberService.getMember(id);
        return MemberResponse.of(member)
    }

    fun signUp(signUpRequest: SignUpRequest) {
        memberService.createMember(
            MemberCommand.Companion.MemberCreateCommand(
                signUpRequest.email,
                signUpRequest.password
            )
        )
    }
}