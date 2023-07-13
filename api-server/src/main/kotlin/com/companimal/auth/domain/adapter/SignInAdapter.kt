package com.companimal.auth.domain.adapter

import com.auth0.jwt.exceptions.JWTCreationException
import com.companimal.auth.domain.constants.AuthenticationConstants
import com.companimal.auth.domain.constants.SignInErrorCode
import com.companimal.auth.domain.constants.SignInSourceType
import com.companimal.auth.domain.dto.SignInHistory
import com.companimal.auth.domain.dto.TokenPublishHistory
import com.companimal.auth.domain.exception.CannotSignInMemberException
import com.companimal.auth.domain.exception.PasswordNotMatchException
import com.companimal.auth.domain.exception.TokenCreationException
import com.companimal.auth.domain.persistence.SignInHistoryWriter
import com.companimal.auth.domain.port.SignInPort
import com.companimal.auth.domain.port.SignInRequest
import com.companimal.auth.domain.port.SignInResponse
import com.companimal.common.domain.exception.CompanimalException
import com.companimal.crypto.domain.port.HashEncoderPort
import com.companimal.member.domain.constants.MemberStatus
import com.companimal.member.domain.dto.Member
import com.companimal.member.domain.persistence.MemberReader
import com.companimal.token.domain.dto.Token
import com.companimal.token.domain.port.CreateTokenPort
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.util.*

@Service
class SignInAdapter(
    private val createTokenPort: CreateTokenPort,
    private val memberReader: MemberReader,
    private val signInHistoryWriter: SignInHistoryWriter,
    private val hashEncoderPort: HashEncoderPort,
): SignInPort {

    override fun signIn(signInRequest: SignInRequest): SignInResponse {
        val member = memberReader.findByEmail(signInRequest.email)
        val sessionId = signInRequest.sessionId ?: UUID.randomUUID().toString()
        val signInSourceType = signInRequest.signInSourceType ?: SignInSourceType.UNKNOWN

        try {
            val tokenInfo = signIn(member, signInRequest)

            saveSignInSuccessHistory(
                memberId = member.id!!,
                sessionId = sessionId,
                signInSourceType = signInSourceType,
                token = tokenInfo
            )

            return SignInResponse(
                sessionId = sessionId,
                token = tokenInfo.token
            )
        } catch (e: CompanimalException) {
            saveSignInFailHistory(
                memberId = member.id!!,
                signInSourceType = signInSourceType,
                exception = e
            )

            throw e
        } catch (e: JWTCreationException) {
            throw TokenCreationException()
        }
    }

    private fun signIn(member: Member, signInRequest: SignInRequest): Token {
        checkPassword(signInRequest, member)
        checkMemberStatus(member)

        val tokenClaimInfo = extractTokenInfoFromMember(member)

        return createTokenPort.createToken(tokenClaimInfo)
    }

    private fun checkPassword(signInRequest: SignInRequest, member: Member) {
        val isPasswordMatch = hashEncoderPort.match(
            plainText = signInRequest.password,
            salt = member.salt,
            encodeText = member.password!!
        )

        if (!isPasswordMatch) {
            throw PasswordNotMatchException()
        }
    }

    private fun checkMemberStatus(member: Member) {
        if (MemberStatus.ACTIVE != member.status) {
            throw CannotSignInMemberException(SignInErrorCode.MEMBER_STATUS_NOT_ACTIVE)
        }

        if (!member.isConfirmed) {
            throw CannotSignInMemberException(SignInErrorCode.MEMBER_NOT_CONFIRMED)
        }
    }

    private fun extractTokenInfoFromMember(member: Member): Map<String, Any> =
        mapOf(
            AuthenticationConstants.TOKEN_CLAIM_USER_ID.value to member.id!!,
            AuthenticationConstants.TOKEN_CLAIM_EMAIL.value to member.email
        )

    private fun saveSignInSuccessHistory(memberId: Long, sessionId: String, signInSourceType: SignInSourceType, token: Token) =
        signInHistoryWriter.addSignInHistory(
            SignInHistory(
                sessionId = sessionId,
                memberId = memberId,
                isSuccess = true,
                signInSourceType = signInSourceType,
                tokenPublishHistoryList = listOf(
                    TokenPublishHistory(
                        jwtId = token.id,
                        publishDateTime = token.issuedAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        expiredDateTime = token.expiredAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        isExpired = false
                    )
                )
            )
        )


    private fun saveSignInFailHistory(memberId: Long, signInSourceType: SignInSourceType, exception: CompanimalException) {
        signInHistoryWriter.addSignInHistory(
            SignInHistory(
                memberId = memberId,
                isSuccess = true,
                signInSourceType = signInSourceType,
                failReason = exception.getErrorMessage()
            )
        )
    }
}