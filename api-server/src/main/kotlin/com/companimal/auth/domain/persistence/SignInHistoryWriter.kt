package com.companimal.auth.domain.persistence

import com.companimal.auth.domain.dto.SignInHistory

interface SignInHistoryWriter {

    fun addSignInHistory(signInHistory: SignInHistory)
}