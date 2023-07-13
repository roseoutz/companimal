package com.companimal.member

import com.companimal.member.domain.constants.MemberStatus
import com.companimal.member.domain.port.CreateMemberRequest
import com.companimal.member.infrastructure.persistence.MemberEntity

object MemberFixture {

    fun memberEntity(
        id: Long? = null,
        email: String = "zeedoutladzz@gmail.com",
        password: String = "26f6430c47cadc8a5a46acc894901775a9bca06a6d2eb25fe1f6d5c3c4c5d59cd963efd7f5a36eb7a61b101ec0410f7c9c36c1bbe18f22f9b0ca997d6296cc19",
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