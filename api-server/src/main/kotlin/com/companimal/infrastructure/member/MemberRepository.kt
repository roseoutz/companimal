package com.companimal.infrastructure.member

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: CrudRepository<MemberEntity, Long?> {

    fun findByEmail(email: String): MemberEntity?
}