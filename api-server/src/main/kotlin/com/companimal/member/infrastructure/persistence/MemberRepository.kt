package com.companimal.member.infrastructure.persistence

import com.companimal.member.infrastructure.persistence.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<MemberEntity, Long?> {

    fun findByEmail(email: String): MemberEntity?
}