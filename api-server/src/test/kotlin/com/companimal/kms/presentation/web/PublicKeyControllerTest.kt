package com.companimal.kms.presentation.web

import com.companimal.kms.ServerKeyFixture
import com.companimal.kms.domain.persistence.ServerKeyReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URI

@AutoConfigureMockMvc
@SpringBootTest
class PublicKeyControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var serverKeyReader: ServerKeyReader

    @Test
    fun `should return valid server public key`() {
        val serverKeyEntity = ServerKeyFixture.serverKey(1L)
        Mockito.`when`(serverKeyReader.findActiveServerKey()).thenReturn(serverKeyEntity)

        val result = mockMvc.perform(
            MockMvcRequestBuilders.get(URI.create("/kms/sign/key"))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val response = result.response

        Assertions.assertTrue { response.contentAsString.contains(serverKeyEntity.publicKey)}
    }
}