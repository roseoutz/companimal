package com.companimal.auth.signIn.infrastructure.persistence

import com.companimal.auth.signIn.domain.persistence.SignInHistoryReader
import org.springframework.stereotype.Service

@Service
class SignInHistoryJpaReader(
    private val signInHistoryRepository: SignInHistoryRepository
): SignInHistoryReader {
}