package com.companimal.kms.infrastructure.persistence

import com.companimal.common.infrastructure.persistence.BaseEntity
import com.companimal.kms.domain.dto.ServerKey
import jakarta.persistence.*

@Entity
@Table(name = "server_key")
class ServerKeyEntity(
    @Column(name = "private_key", length = 4000)
    val privateKey: String,

    @Column(name = "public_key", length = 4000)
    val publicKey: String,

    @Column(name = "key_size")
    val keySize: Int,

    @Column(name = "algorithm")
    val algorithm: String,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseEntity() {

    fun toServerKey(): ServerKey =
        ServerKey(
            privateKey = this.privateKey,
            publicKey = this.publicKey,
            keySize = this.keySize,
            algorithm = this.algorithm,
            isDeleted = this.isDeleted,
            id = this.id,
            createdDatetime = this.createdDatetime,
            updatedDatetime = this.updatedDatetime,
        )

    fun deleteServerKey() {
        this.isDeleted = true
    }

    companion object {
        fun of(serverKey: ServerKey): ServerKeyEntity =
            ServerKeyEntity(
                id = serverKey.id,
                publicKey = serverKey.publicKey,
                privateKey = serverKey.privateKey,
                keySize = serverKey.keySize,
                algorithm = serverKey.algorithm,
                isDeleted = serverKey.isDeleted,
            )
    }
}