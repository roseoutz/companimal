package com.companimal.infrastructure.common.persistence.jpa

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ConditionalOnProperty(prefix = "spring.jpa", name = ["database-platform"])
@Configuration
class JpaConfiguration {
}