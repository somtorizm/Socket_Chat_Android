package com.vectorincng.socketchat.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KtorRealtimeMessagingClient(private val client: HttpClient): RealTimeMessagingClient {
    private var session : WebSocketSession? = null

    override fun getAppStateStream(): Flow<MessageState> {
        return callbackFlow {
            var session: DefaultClientWebSocketSession? = null
            var username = "somto"

            while (true) {
                try {
                    session = client.webSocketSession {
                        url("ws://192.168.1.22:8080/chat-socket?username=$username")
                    }

                    session.incoming.consumeAsFlow()
                        .filterIsInstance<Frame.Text>()
                        .mapNotNull { frame ->
                            try {
                                Json.decodeFromString<MessageState>(frame.readText())
                            } catch (e: SerializationException) {
                                null
                            }
                        }
                        .collect { messageState ->
                            trySend(messageState).isSuccess
                        }
                } catch (e: Exception) {
                    println("WebSocket connection failed: ${e.message}")
                    trySend(MessageState("WebSocket connection failed: ${e.message}")).isSuccess
                } finally {
                    session?.close(CloseReason(CloseReason.Codes.NORMAL, "WebSocket session closed"))
                    session = null
                }
            }
        }
    }

    override suspend fun sendAction(action: String) {
        session?.outgoing?.send(
            Frame.Text("make_turn#${Json.encodeToString(action)}")
        )
    }

    override suspend fun closeConnection() {
        session?.close()
        session = null
    }
}