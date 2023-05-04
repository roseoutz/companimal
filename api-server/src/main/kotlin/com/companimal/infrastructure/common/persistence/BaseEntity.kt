package com.companimal.infrastructure.common.persistence

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity (
    @CreationTimestamp
    @Column(updatable = false)
    val createdDatetime: LocalDateTime? = null,

    @UpdateTimestamp
    @Column
    var updatedDatetime: LocalDateTime? = null
)