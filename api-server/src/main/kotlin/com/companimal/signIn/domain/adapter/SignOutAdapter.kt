package com.companimal.signIn.domain.adapter

import com.companimal.member.domain.persistence.MemberReader
import com.companimal.signIn.domain.port.SignOutRequest
import com.companimal.signIn.domain.port.SignOutPort
import org.springframework.stereotype.Service

@Service
class SignOutAdapter(
    private val memberReader: MemberReader,
): SignOutPort {
    override fun signOut(signOutRequest: SignOutRequest) {
        TODO("Not yet implemented")
    }
}