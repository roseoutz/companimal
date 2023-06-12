package com.companimal.signIn.infrastructure.persistence

import com.companimal.signIn.domain.dto.SignInHistory
import com.companimal.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*

@Entity
@Table(
    name = "sign_in_history",
    indexes = [Index(name = "idx_sign_in_history_session_id", columnList = "session_id")]
)
class SignInHistoryEntity(

    @Column(name = "session_id", length = 36, unique = true, nullable = false)
    var sessionId: String,

    @Column(length = 32, unique = true, nullable = false)
    var memberId: Long,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "sessionId")
    val tokenPublishHistoryList: MutableList<TokenPublishHistoryEntity> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {

    fun toSignInHistory(): SignInHistory =
        SignInHistory(
            sessionId = this.sessionId,
            memberId = this.memberId,
            tokenPublishHistoryList = this.tokenPublishHistoryList.map { it.toTokenPublishHistory() },
            id = this.id,
            createdDatetime = this.createdDatetime,
            updatedDatetime = this.updatedDatetime,
        )


    companion object {
        fun of(signInHistory: SignInHistory): SignInHistoryEntity =
            SignInHistoryEntity(
                sessionId = signInHistory.sessionId,
                memberId = signInHistory.memberId,
                tokenPublishHistoryList = signInHistory.tokenPublishHistoryList.map { TokenPublishHistoryEntity.of(it) }.toMutableList(),
                id = signInHistory.id
            )
    }
}