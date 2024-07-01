package com.vectorincng.socketchat.data.remote

import com.vectorincng.socketchat.domain.model.Message

interface MessageService {
    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "http://192.168.1.21:8080"
    }

    sealed class Endpoints(val url: String) {
        data object GetAllMessages: Endpoints("$BASE_URL/messages")
    }
}