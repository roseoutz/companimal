package com.companimal.domain.member.command

import com.companimal.domain.member.constants.MemberStatus
import com.companimal.domain.member.dto.Member

class MemberCommand {
    companion object {
        data class MemberCreateCommand(
            val email: String,
            val password: String,
        ) {

            fun toMember(): Member {
                return Member(
                    email = email,
                    password = password,
                    confirm = false,
                    status = MemberStatus.IN_ACTIVE
                )
            }
        }
    }
}