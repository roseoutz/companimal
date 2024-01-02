package com.companimal.auth.infrastructure.persistence

import com.companimal.auth.domain.dto.TokenPublishHistory
import com.companimal.auth.domain.persistence.TokenPublishHistoryReader
import org.springframework.stereotype.Component

@Component
class TokenPublishHistoryJpaReader(
    private val tokenPublishHistoryRepository: TokenPublishHistoryRepository
): TokenPublishHistoryReader {
    override fun getTokenPublishHistory(jwtId: String): TokenPublishHistory? =
        tokenPublishHistoryRepository.findByJwtId(jwtId)?.toTokenPublishHistory()


}