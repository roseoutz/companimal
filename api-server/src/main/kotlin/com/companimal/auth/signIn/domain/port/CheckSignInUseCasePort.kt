package com.companimal.auth.signIn.domain.port

interface CheckSignInUseCasePort {
    fun checkLogin(token: String)
}