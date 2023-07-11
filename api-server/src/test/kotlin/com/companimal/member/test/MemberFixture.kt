package com.companimal.member.test

import com.companimal.member.domain.constants.MemberStatus
import com.companimal.member.domain.port.CreateMemberRequest
import com.companimal.member.infrastructure.persistence.MemberEntity

object MemberFixture {

    fun memberEntity(
        id: Long = 1,
        email: String = "zeedoutladzz@gmail.com",
        password: String = "testpassword123",
        salt: String = "salt1234",
        confirm: Boolean = true,
        memberStatus: MemberStatus = MemberStatus.ACTIVE,
    ): MemberEntity =
        MemberEntity(
            id = id,
            email = email,
            password = password,
            salt = salt,
            confirm = confirm,
            status = memberStatus
        )

    fun createMemberRequest(
        email: String = "zeedoutladzz@gmail.com",
        password: String = "testpassword1234",
        salt: String = "salt1234"
    ): CreateMemberRequest =
        CreateMemberRequest(
            email = email,
            password = password,
            salt = salt,
        )
}