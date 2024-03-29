package com.companimal.member.infrastructure.persistence

import com.companimal.common.infrastructure.persistence.BaseEntity
import com.companimal.member.domain.constants.MemberStatus
import com.companimal.member.domain.dto.Member
import jakarta.persistence.*

@Entity
@Table(
    name = "member",
    indexes = [Index(name = "idx_member_email", columnList = "email")]
)
class MemberEntity (

    @Column(name = "email", length = 32, unique = true, nullable = false)
    var email: String,

    @Column(name = "is_confirmed")
    var isConfirmed: Boolean = false,

    @Column(name = "password", length = 50, columnDefinition = "text", nullable = false)
    var password: String? = null,

    @Column(name = "salt", length = 32, columnDefinition = "text", nullable = false)
    var salt: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: MemberStatus? = MemberStatus.ACTIVE,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    ): BaseEntity() {
    fun toMember(): Member =
        Member(
            email = this.email,
            password = this.password,
            salt = this.salt,
            isConfirmed = this.isConfirmed,
            status = this.status,
            id = this.id,
            createdDatetime = this.createdDatetime,
            updatedDatetime = this.updatedDatetime
        )


    fun deleteMember() {
        this.status = MemberStatus.DELETED
    }

    fun updatePassword(password: String, salt: String) {
        this.password = password
        this.salt = salt
    }

    companion object {
        fun of(member: Member): MemberEntity = MemberEntity(
            id = member.id,
            email = member.email,
            password = member.password,
            salt = member.salt,
            isConfirmed = member.isConfirmed,
            status = member.status!!
        )
    }
}