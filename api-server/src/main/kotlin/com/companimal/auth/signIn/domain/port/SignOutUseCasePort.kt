package com.companimal.auth.signIn.domain.port

interface SignOutUseCasePort {
    fun signOut(signOutRequest: com.companimal.auth.signIn.domain.port.SignOutRequest);
}

data class SignOutRequest(
    val id: String,
    val token: String,
)