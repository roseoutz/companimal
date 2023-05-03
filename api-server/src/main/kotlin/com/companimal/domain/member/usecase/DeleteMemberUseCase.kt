package com.companimal.domain.member.usecase

interface DeleteMemberUseCase {
    fun delete(deleteMemberRequest: DeleteMemberRequest)
}

data class DeleteMemberRequest(
    val id: Long,
)