package com.companimal.auth.domain.persistence

import com.companimal.auth.domain.dto.TokenPublishHistory

interface TokenPublishHistoryReader {

    fun getTokenPublishHistory(jwtId: String): TokenPublishHistory?
}