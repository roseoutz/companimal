package com.companimal.auth.infrastructure.persistence

import com.companimal.AbstractDataJpaTest
import com.companimal.auth.domain.constants.SignInSourceType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.util.*

@Transactional
class SignInHistoryPersistenceTest @Autowired private constructor(
    private val signInHistoryRepository: SignInHistoryRepository,
    private val tokenPublishHistoryRepository: TokenPublishHistoryRepository
): AbstractDataJpaTest() {

    private fun getSignInHistoryEntity(): SignInHistoryEntity =
        SignInHistoryEntity(
            sessionId = UUID.randomUUID().toString(),
            memberId = 1L,
        )

    @DisplayName("로그인 이력이 정상 저장된다.")
    @Test
    fun `should save sign in history`() {
        val entity = getSignInHistoryEntity()
        signInHistoryRepository.save(entity)

        val findOne = signInHistoryRepository.findByIdOrNull(1L) ?: Assertions.fail()

        assertThat(findOne.sessionId).isEqualTo(entity.sessionId)
    }

    @DisplayName("로그인 이력 및 Token 발행 이력이 정상 저장된다.")
    @Test
    fun `should save sign in history with token publish`() {
        val entity = getSignInHistoryEntity().let {
            it.tokenPublishHistoryList.add(
                TokenPublishHistoryEntity(
                sessionId = it.sessionId,
                signInSourceType = SignInSourceType.WEB,
                expiredDateTime = LocalDateTime.now()
            )
            )
            it
        }

        signInHistoryRepository.save(entity)

        val findOne = signInHistoryRepository.findByIdOrNull(1L) ?: Assertions.fail()
        val tokenFindOne = tokenPublishHistoryRepository.findByIdOrNull(1L) ?: fail(RuntimeException("Token Not Saved"))

        assertThat(findOne.sessionId).isEqualTo(entity.sessionId)
        assertThat(tokenFindOne.sessionId).isEqualTo(entity.sessionId)
    }

}