package com.companimal.kms.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServerKeyRepository: JpaRepository<ServerKeyEntity, Long> {

    fun findByDeleted(isDeleted: Boolean = false): ServerKeyEntity
}