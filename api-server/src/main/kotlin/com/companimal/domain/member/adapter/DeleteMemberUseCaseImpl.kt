package com.companimal.domain.member.adapter

import com.companimal.domain.member.persistence.MemberStore
import com.companimal.domain.member.port.DeleteMemberRequest
import com.companimal.domain.member.port.DeleteMemberUseCase
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