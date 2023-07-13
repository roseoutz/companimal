package com.companimal.kms.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServerKeyRepository: JpaRepository<ServerKeyEntity, Long> {

    fun findByIsDeleted(isDeleted: Boolean = false): ServerKeyEntity?
}