package com.companimal.auth.signIn.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TokenPublishHistoryRepository: JpaRepository<com.companimal.auth.signIn.infrastructure.persistence.TokenPublishHistoryEntity, Long> {
}