package com.companimal.signIn.domain.port

interface SignInByTokenPort {
    fun signIn(token: String);
}
