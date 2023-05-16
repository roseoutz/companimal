package com.companimal.auth.member.infrastructure.persistence

import com.companimal.common.infrastructure.persistence.BaseEntity
import com.companimal.auth.member.domain.constants.MemberStatus
import com.companimal.auth.member.domain.constants.AuthenticationProvider
import com.companimal.auth.member.domain.dto.Member
import jakarta.persistence.*

@Entity
@Table(
    name = "member",
    indexes = [Index(name = "idx_member_email", columnList = "email")]
)
class MemberEntity (

    @Column(name = "email", length = 32, unique = true, nullable = false)
    var email: String,

    @Column(name = "confirm")
    var confirm: Boolean = false,

    @Column(name = "password", length = 50, columnDefinition = "text", nullable = false)
    var password: String? = null,

    @Column(name = "salt", length = 32, columnDefinition = "text", nullable = false)
    var salt: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_provider")
    var authenticationProvider: AuthenticationProvider? = AuthenticationProvider.NONE,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: MemberStatus? = MemberStatus.ACTIVE,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    ): BaseEntity() {
    fun toMember(): Member {
        return Member(
            email = this.email,
            password = this.password,
            salt = this.salt,
            authenticationProvider = this.authenticationProvider,
            confirm = this.confirm,
            status = this.status,
            id = this.id,
            createdDatetime = this.createdDatetime,
            updatedDatetime = this.updatedDatetime
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
            salt = member.salt,
            authenticationProvider = member.authenticationProvider,
            confirm = member.confirm,
            status = member.status!!
        )
    }
}