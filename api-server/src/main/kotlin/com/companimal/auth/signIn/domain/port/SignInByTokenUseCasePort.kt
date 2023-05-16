package com.companimal.auth.signIn.domain.port

interface SignInByTokenUseCasePort {
    fun signIn(token: String);
}
