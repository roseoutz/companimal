package com.companimal.auth.member.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<MemberEntity, Long?> {

    fun findByEmail(email: String): MemberEntity?
}