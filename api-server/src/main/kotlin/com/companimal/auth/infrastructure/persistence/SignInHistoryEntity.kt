package com.companimal.auth.infrastructure.persistence

import com.companimal.auth.domain.constants.SignInSourceType
import com.companimal.auth.domain.dto.SignInHistory
import com.companimal.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*

@Entity
@Table(
    name = "sign_in_history",
    indexes = [Index(name = "idx_sign_in_history_session_id", columnList = "session_id")]
)
class SignInHistoryEntity(

    @Column(name = "session_id", length = 128, unique = true)
    val sessionId: String? = null,

    @Column(name = "member_id", nullable = false)
    val memberId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_source_type")
    val signInSourceType: SignInSourceType,

    @Column(name = "is_success")
    val isSuccess: Boolean,

    @Column(name = "fail_reason", length = 32)
    val failReason: String? = null,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "jwtId")
    val tokenPublishHistoryList: MutableList<TokenPublishHistoryEntity> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {

    fun toSignInHistory(): SignInHistory =
        SignInHistory(
            id = this.id,
            sessionId = this.sessionId,
            memberId = this.memberId,
            signInSourceType = this.signInSourceType,
            isSuccess = this.isSuccess,
            failReason = this.failReason,
            tokenPublishHistoryList = this.tokenPublishHistoryList.map { it.toTokenPublishHistory() },
            createdDatetime = this.createdDatetime,
            updatedDatetime = this.updatedDatetime,
        )


    companion object {
        fun of(signInHistory: SignInHistory): SignInHistoryEntity =
            SignInHistoryEntity(
                id = signInHistory.id,
                sessionId = signInHistory.sessionId,
                memberId = signInHistory.memberId,
                signInSourceType = signInHistory.signInSourceType,
                isSuccess = signInHistory.isSuccess,
                failReason = signInHistory.failReason,
                tokenPublishHistoryList = signInHistory.tokenPublishHistoryList.map { TokenPublishHistoryEntity.of(it) }.toMutableList()
            )
    }
}