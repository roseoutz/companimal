package com.companimal.adapter.member.out.persistence

import com.companimal.application.member.constants.MemberStatus
import com.companimal.adapter.common.out.BaseEntity
import com.companimal.domain.member.Member
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

    @Enumerated(EnumType.STRING)
    var status: MemberStatus = MemberStatus.ACTIVE,

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
            this.status,
            this.createdDatetime,
            this.updatedDatetime
        )
    }

    fun deleteMember(): MemberEntity {
        this.status = MemberStatus.DELETED
        return this
    }

    companion object {
        fun of(member: Member): MemberEntity = MemberEntity(
            id = member.id,
            email = member.email,
            password = member.password,
            confirm = member.confirm,
            status = member.status
        )
    }
}