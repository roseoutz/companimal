package com.companimal.auth.infrastructure.persistence

import com.companimal.auth.domain.persistence.SignInHistoryReader
import org.springframework.stereotype.Service

@Service
class SignInHistoryJpaReader(
    private val signInHistoryRepository: SignInHistoryRepository
): SignInHistoryReader {
}