package com.companimal.auth.infrastructure.persistence

import com.companimal.auth.domain.constants.OAuthServiceProvider
import jakarta.persistence.*

@Entity
class SignInInfoEntity(
    @Column(length = 32, unique = true, nullable = false)
    val memberId: Long,

    @Enumerated(EnumType.STRING)
    val oAuthServiceProvider: OAuthServiceProvider,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
) {
}