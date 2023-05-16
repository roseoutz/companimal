package com.companimal.auth.signIn.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SignInHistoryRepository: JpaRepository<com.companimal.auth.signIn.infrastructure.persistence.SignInHistoryEntity, Long> {
}