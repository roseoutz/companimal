package com.companimal.auth.domain.port

interface SignInByTokenUseCasePort {
    fun signIn(token: String);
}
