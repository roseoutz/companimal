package com.companimal.auth.infrastructure.persistence

import com.companimal.auth.domain.dto.SignInHistory
import com.companimal.auth.domain.persistence.SignInHistoryWriter
import org.springframework.stereotype.Component

@Component
class SignInHistoryJpaWriter(
    private val signInHistoryRepository: SignInHistoryRepository
): SignInHistoryWriter {
    override fun addSignInHistory(signInHistory: SignInHistory) {
        signInHistoryRepository.save(SignInHistoryEntity.of(signInHistory))
    }
}