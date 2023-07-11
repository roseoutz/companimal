package com.companimal.signIn.domain.port

interface SignOutPort {
    fun signOut(signOutRequest: SignOutRequest);
}

data class SignOutRequest(
    val id: String,
    val token: String,
)