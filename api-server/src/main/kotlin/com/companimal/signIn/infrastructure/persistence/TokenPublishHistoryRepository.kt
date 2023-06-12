package com.companimal.signIn.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TokenPublishHistoryRepository: JpaRepository<TokenPublishHistoryEntity, Long> {
}