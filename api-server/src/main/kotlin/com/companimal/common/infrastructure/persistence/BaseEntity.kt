package com.companimal.common.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity (
    @CreationTimestamp
    @Column(name = "created_datetime", updatable = false)
    val createdDatetime: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_datetime")
    var updatedDatetime: LocalDateTime? = null
)