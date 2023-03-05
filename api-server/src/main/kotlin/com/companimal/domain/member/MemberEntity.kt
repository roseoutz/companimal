package com.companimal.domain.member

import com.companimal.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_member_email", columnList = "email")])
class MemberEntity (
    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column
    var confirm: Boolean = false
): BaseEntity() {


}