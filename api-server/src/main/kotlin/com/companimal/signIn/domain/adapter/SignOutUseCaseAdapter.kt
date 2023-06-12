package com.companimal.signIn.domain.adapter

import com.companimal.auth.member.domain.persistence.MemberReader
import com.companimal.signIn.domain.port.SignOutRequest
import com.companimal.signIn.domain.port.SignOutUseCasePort
import org.springframework.stereotype.Service

@Service
class SignOutUseCaseAdapter(
    private val memberReader: MemberReader,
): SignOutUseCasePort {
    override fun signOut(signOutRequest: SignOutRequest) {
        TODO("Not yet implemented")
    }
}