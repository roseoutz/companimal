package com.companimal.domain.member.persistence

import com.companimal.domain.member.dto.Member

interface MemberReader {

    fun findById(id: Long): Member

    fun findByEmail(email: String): Member

}