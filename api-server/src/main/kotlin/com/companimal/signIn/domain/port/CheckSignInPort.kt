package com.companimal.signIn.domain.port

interface CheckSignInPort {
    fun checkLogin(token: String)
}