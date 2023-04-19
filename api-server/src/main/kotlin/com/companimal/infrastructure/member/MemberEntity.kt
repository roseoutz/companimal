package com.companimal.infrastructure.member

import com.companimal.infrastructure.common.entity.BaseEntity
import com.companimal.domain.member.models.Member
import jakarta.persistence.*

@Entity
@Table(indexes = [Index(name = "idx_member_email", columnList = "email")])
class MemberEntity (

    @Column(length = 32, unique = true, nullable = false)
    var email: String,

    @Column(length = 50, columnDefinition = "text", nullable = false)
    var password: String,

    @Column
    var confirm: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {

    fun toMember(): Member {
        return Member(
            this.id,
            this.email,
            this.password,
            this.confirm,
            this.createdDatetime,
            this.updatedDatetime
        )
    }
}