package com.companimal.signIn.domain.dto

import com.companimal.signIn.domain.constants.OAuthServiceProvider

data class SignInInfo (
    val id: Long,
    val memberId: Long,
    val oAuthServiceProvider: OAuthServiceProvider,
)