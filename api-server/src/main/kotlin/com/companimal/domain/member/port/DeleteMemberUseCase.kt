package com.companimal.domain.member.port

interface DeleteMemberUseCase {
    fun delete(deleteMemberRequest: DeleteMemberRequest)
}

data class DeleteMemberRequest(
    val id: Long,
)