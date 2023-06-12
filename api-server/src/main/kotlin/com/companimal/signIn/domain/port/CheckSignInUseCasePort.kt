package com.companimal.signIn.domain.port

interface CheckSignInUseCasePort {
    fun checkLogin(token: String)
}