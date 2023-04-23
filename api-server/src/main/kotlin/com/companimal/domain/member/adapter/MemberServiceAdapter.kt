package com.companimal.domain.member.adapter

import com.companimal.domain.member.port.out.MemberPersistencePort
import org.springframework.stereotype.Service

@Service
class MemberServiceAdapter(
    private val memberPersistencePort: MemberPersistencePort
) {

}