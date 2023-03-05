package com.companimal.infrastructure.member

import com.companimal.domain.member.MemberEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: CrudRepository<MemberEntity, Long?> {
}