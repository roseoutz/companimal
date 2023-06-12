package com.companimal.signIn.domain.port

interface SignOutUseCasePort {
    fun signOut(signOutRequest: SignOutRequest);
}

data class SignOutRequest(
    val id: String,
    val token: String,
)