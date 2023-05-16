package com.companimal.auth.signIn.domain.adapter

import com.companimal.auth.member.domain.persistence.MemberReader
import org.springframework.stereotype.Service

@Service
class SignOutUseCaseAdapter(
    private val memberReader: MemberReader,
): com.companimal.auth.signIn.domain.port.SignOutUseCasePort {
    override fun signOut(signOutRequest: com.companimal.auth.signIn.domain.port.SignOutRequest) {
        TODO("Not yet implemented")
    }
}