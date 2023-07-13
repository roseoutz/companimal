package com.companimal.auth.infrastructure.persistence

import com.companimal.auth.domain.dto.TokenPublishHistory
import com.companimal.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "token_publish_history",
)
class TokenPublishHistoryEntity(

    @Column(name = "jwt_id", length = 128, nullable = false)
    var jwtId: String,

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
            jwtId = this.jwtId,
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
                jwtId = tokenPublishHistory.jwtId,
                expiredDateTime = tokenPublishHistory.expiredDateTime,
                publishDateTime = tokenPublishHistory.publishDateTime,
                isExpired = tokenPublishHistory.isExpired,
                id = tokenPublishHistory.id
            )
    }
}