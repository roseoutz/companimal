package com.companimal.auth.domain.port

interface SignOutUseCasePort {
    fun signOut(token: String);
}
