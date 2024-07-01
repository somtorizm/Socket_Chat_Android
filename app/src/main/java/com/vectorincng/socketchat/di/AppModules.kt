package com.vectorincng.socketchat.di

import com.vectorincng.socketchat.data.remote.ChatSocketImpl
import com.vectorincng.socketchat.data.remote.ChatSocketService
import com.vectorincng.socketchat.data.remote.MessageService
import com.vectorincng.socketchat.data.remote.MessageServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
        }
    }

    @Singleton
    @Provides
    fun provideMessageService(httpClient: HttpClient): MessageService {
        return MessageServiceImpl(httpClient)
    }

    @Singleton
    @Provides
    fun provideChatService(httpClient: HttpClient): ChatSocketService {
        return ChatSocketImpl(httpClient)
    }
}