package com.companimal.member.infrastructure.persistence

import com.companimal.auth.member.infrastructure.persistence.MemberEntity
import com.companimal.auth.member.infrastructure.persistence.MemberRepository
import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.QueryFactoryImpl
import com.linecorp.kotlinjdsl.query.creator.CriteriaQueryCreatorImpl
import com.linecorp.kotlinjdsl.query.creator.SubqueryCreatorImpl
import com.linecorp.kotlinjdsl.querydsl.expression.column
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class MemberJDSLTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val entityManager: EntityManager,
): AbstractDataJpaTest() {

    private fun getQueryFactory(): QueryFactory = QueryFactoryImpl(
        criteriaQueryCreator = CriteriaQueryCreatorImpl(entityManager),
        subqueryCreator = SubqueryCreatorImpl()
    )

    private fun getMemberEntity(
        email: String = "test@companimal.com",
        password: String = "test1234",
        salt: String = "testestest",
        confirm: Boolean = false,
    ): MemberEntity = MemberEntity(
        email = email,
        password = password,
        salt = salt,
        confirm = confirm,
    )

    @Test
    fun `should get member data`() {
        val entity = getMemberEntity()
        memberRepository.save(entity)

        val findOne = getQueryFactory().selectQuery(MemberEntity::class.java) {
            select(entity(MemberEntity::class))
            from(entity(MemberEntity::class))
            where(
                and(column(MemberEntity::id).equal(1L))
            )
        }.resultStream.findFirst() ?: Assertions.fail()


        Assertions.assertEquals(entity.email, findOne.get().email)
    }
}