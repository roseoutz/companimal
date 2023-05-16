package com.companimal.auth.domain.port

interface CheckSignInUseCasePort {
    fun checkLogin(token: String)
}