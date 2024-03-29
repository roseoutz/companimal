package com.companimal.auth.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SignInInfoRepository: JpaRepository<SignInInfoEntity, Long> {
}