package com.companimal.auth.domain.adapter

import com.companimal.auth.domain.port.SignOutUseCasePort
import com.companimal.member.domain.persistence.MemberReader
import org.springframework.stereotype.Service

@Service
class SignOutUseCaseAdapter(
    private val memberReader: MemberReader,
): SignOutUseCasePort {
    override fun signOut(token: String) {
        TODO("Not yet implemented")
    }
}