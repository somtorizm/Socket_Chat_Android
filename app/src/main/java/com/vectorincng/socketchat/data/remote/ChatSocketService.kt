package com.vectorincng.socketchat.data.remote

import com.vectorincng.socketchat.domain.model.Message
import com.vectorincng.socketchat.utils.State
import kotlinx.coroutines.flow.Flow


interface ChatSocketService {
    suspend fun initSession(username: String): State<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://192.168.1.21:8080"
    }

    sealed class Endpoints(val url: String) {
        data object ChatSocket: Endpoints("$BASE_URL/chat-socket")
    }
}