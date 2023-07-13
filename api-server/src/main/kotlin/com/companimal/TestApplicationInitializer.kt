package com.companimal

import com.companimal.kms.domain.port.CreateServerKeyPort
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("test")
@Component
class TestApplicationInitializer(
    private val createServerKeyPort: CreateServerKeyPort,
): InitializingBean {
    override fun afterPropertiesSet() {
        createServerKeyPort.createServerKey()
    }
}