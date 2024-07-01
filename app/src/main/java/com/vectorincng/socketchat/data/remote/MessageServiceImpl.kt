package com.vectorincng.socketchat.data.remote

import com.vectorincng.socketchat.data.remote.dto.MessageDto
import com.vectorincng.socketchat.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MessageServiceImpl(private val client: HttpClient) : MessageService{
    override suspend fun getAllMessages(): List<Message> {
        return try {
           val response = client.get(MessageService.Endpoints.GetAllMessages.url).body<List<MessageDto>>()
               response.map { it.returnMessage() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}