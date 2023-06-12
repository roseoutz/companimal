package com.companimal.signIn.infrastructure.persistence

import com.companimal.signIn.domain.persistence.SignInHistoryReader
import org.springframework.stereotype.Service

@Service
class SignInHistoryJpaReader(
    private val signInHistoryRepository: SignInHistoryRepository
): SignInHistoryReader {
}