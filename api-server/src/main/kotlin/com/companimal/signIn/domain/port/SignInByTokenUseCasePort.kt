package com.companimal.signIn.domain.port

interface SignInByTokenUseCasePort {
    fun signIn(token: String);
}
