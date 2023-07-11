package com.companimal.member

import com.companimal.member.domain.constants.MemberStatus
import com.companimal.member.domain.port.CreateMemberRequest
import com.companimal.member.infrastructure.persistence.MemberEntity

object MemberFixture {

    fun memberEntity(
        id: Long? = null,
        email: String = "zeedoutladzz@gmail.com",
        password: String = "TestPassword123!!",
        salt: String = "salt1234",
        confirm: Boolean = true,
        memberStatus: MemberStatus = MemberStatus.ACTIVE,
    ): MemberEntity =
        MemberEntity(
            id = id,
            email = email,
            password = password,
            salt = salt,
            isConfirmed = confirm,
            status = memberStatus
        )

    fun createMemberRequest(
        email: String = "zeedoutladzz@gmail.com",
        password: String = "TestPassword123!!",
    ): CreateMemberRequest =
        CreateMemberRequest(
            email = email,
            password = password,
        )

}