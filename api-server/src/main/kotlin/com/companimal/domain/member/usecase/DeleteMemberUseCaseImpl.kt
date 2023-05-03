package com.companimal.domain.member.usecase

import com.companimal.domain.member.persistence.MemberStore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteMemberUseCaseImpl(
    private val memberStore: MemberStore,
): DeleteMemberUseCase {

    @Transactional
    override fun delete(deleteMemberRequest: DeleteMemberRequest) {
        memberStore.deleteMember(deleteMemberRequest.id)
    }
}