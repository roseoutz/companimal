package com.companimal.auth.member.domain.persistence

import com.companimal.auth.member.domain.dto.Member

interface MemberReader {

    fun findById(id: Long): Member?


    fun findByEmail(email: String): Member?


}