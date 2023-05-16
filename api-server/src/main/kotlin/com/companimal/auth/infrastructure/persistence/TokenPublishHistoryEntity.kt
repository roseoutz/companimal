package com.companimal.auth.infrastructure.persistence

import com.companimal.auth.domain.constants.SignInSourceType
import com.companimal.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "token_publish_history",
)
class TokenPublishHistoryEntity(

    @Column(name = "session_id", length = 32, nullable = false)
    var sessionId: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_source_type")
    val signInSourceType: SignInSourceType,

    @Column(name = "expired_datetime")
    var expiredDateTime: LocalDateTime,

    @Column(name = "publish_datetime")
    var publishDateTime: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "is_expired")
    var isExpired: Boolean? = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {
}