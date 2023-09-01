package com.companimal.auth.domain.port

interface SignInByTokenPort {
    fun signIn(token: String)
}
