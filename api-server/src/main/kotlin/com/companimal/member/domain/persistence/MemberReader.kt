package com.companimal.member.domain.persistence

import com.companimal.member.domain.dto.Member

interface MemberReader {

    fun findById(id: Long): Member?


    fun findByEmail(email: String): Member?


}