package com.companimal.auth.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TokenPublishHistoryRepository: JpaRepository<TokenPublishHistoryEntity, Long> {
}