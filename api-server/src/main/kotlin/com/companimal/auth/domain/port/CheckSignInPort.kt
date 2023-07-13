package com.companimal.auth.domain.port

interface CheckSignInPort {
    fun checkLogin(token: String)
}