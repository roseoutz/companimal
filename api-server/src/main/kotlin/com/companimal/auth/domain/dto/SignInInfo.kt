package com.companimal.auth.domain.dto

import com.companimal.auth.domain.constants.OAuthServiceProvider

data class SignInInfo (
    val id: Long,
    val memberId: Long,
    val oAuthServiceProvider: OAuthServiceProvider,
)