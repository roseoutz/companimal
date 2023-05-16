package com.companimal.auth.infrastructure.persistence

import com.companimal.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*

@Entity
@Table(
    name = "sign_in_history",
    indexes = [Index(name = "idx_sign_in_history_session_id", columnList = "session_id")]
)
class SignInHistoryEntity(

    @Column(name = "session_id", length = 32, unique = true, nullable = false)
    var sessionId: String,

    @Column(length = 32, unique = true, nullable = false)
    var memberId: String,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "session_id")
    val tokenPublishHistoryList: List<TokenPublishHistoryEntity> = listOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {
}