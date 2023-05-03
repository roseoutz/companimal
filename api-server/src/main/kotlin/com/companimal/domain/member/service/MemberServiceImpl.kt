package com.companimal.domain.member.service

import com.companimal.domain.crypto.service.HashEncoderService
import com.companimal.domain.member.command.MemberCommand
import com.companimal.domain.member.dto.Member
import com.companimal.domain.member.exception.PasswordPolicyValidateException
import com.companimal.domain.member.persistence.MemberReader
import com.companimal.domain.member.persistence.MemberStore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberReader: MemberReader,
    private val memberStore: MemberStore,
    private val hashEncoderService: HashEncoderService,
): MemberService {

    override fun getMember(id: Long): Member {
        return memberReader.findById(id)
    }

    override fun getMemberByEmail(email: String): Member {
        return memberReader.findByEmail(email)
    }

    @Transactional
    override fun createMember(memberCreateCommand: MemberCommand.Companion.MemberCreateCommand) {
        val member = memberCreateCommand.toMember()
        val salt = hashEncoderService.getSaltValue()
        val encoded = hashEncoderService.encode(member.password, salt)
        memberStore.addMember(
            Member(
                email = member.email,
                password = encoded,
                salt = salt,
                confirm = member.confirm,
                status = member.status)
        )
    }

    @Transactional
    override fun deleteMember(id: Long) {
        memberStore.deleteMember(id)
    }

    @Transactional
    override fun updatePassword(memberPasswordChangeCommand: MemberCommand.Companion.MemberPasswordChangeCommand) {

        if (memberPasswordChangeCommand.newPassword == memberPasswordChangeCommand.oldPassword) {
            throw PasswordPolicyValidateException()
        }

        val encoded = hashEncoderService.encode(memberPasswordChangeCommand.newPassword)
        memberStore.updatePassword(memberPasswordChangeCommand.id, encoded)
    }

}