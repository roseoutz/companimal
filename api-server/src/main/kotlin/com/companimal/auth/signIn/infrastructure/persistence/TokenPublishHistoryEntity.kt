package com.companimal.auth.signIn.infrastructure.persistence

import com.companimal.auth.signIn.domain.constants.SignInSourceType
import com.companimal.auth.signIn.domain.dto.TokenPublishHistory
import com.companimal.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "token_publish_history",
)
class TokenPublishHistoryEntity(

    @Column(name = "session_id", length = 36, nullable = false)
    var sessionId: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_source_type")
    val signInSourceType: SignInSourceType,

    @Column(name = "expired_datetime")
    var expiredDateTime: LocalDateTime,

    @Column(name = "publish_datetime")
    var publishDateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "is_expired")
    var isExpired: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {

    fun toTokenPublishHistory(): TokenPublishHistory =
        TokenPublishHistory(
            sessionId = this.sessionId,
            signInSourceType = this.signInSourceType,
            expiredDateTime = this.expiredDateTime,
            publishDateTime = this.publishDateTime,
            isExpired = this.isExpired,
            id = this.id,
            createdDatetime = this.createdDatetime,
            updatedDatetime = this.updatedDatetime,
        )

    companion object {
        fun of(tokenPublishHistory: TokenPublishHistory): TokenPublishHistoryEntity =
            TokenPublishHistoryEntity(
                sessionId = tokenPublishHistory.sessionId,
                signInSourceType = tokenPublishHistory.signInSourceType,
                expiredDateTime = tokenPublishHistory.expiredDateTime,
                publishDateTime = tokenPublishHistory.publishDateTime,
                isExpired = tokenPublishHistory.isExpired,
                id = tokenPublishHistory.id
            )
    }
}