package com.vectorincng.socketchat.data.remote.dto

import com.vectorincng.socketchat.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.Date

@Serializable
data class MessageDto(
    val text: String,
    val username: String,
    val timeStamp: Long,
    val id: String
) {
    fun returnMessage(): Message {
        val date = Date(timeStamp)
        val time = DateFormat.getTimeInstance(DateFormat.DEFAULT).format(date)
        return Message(text, time, username )
    }
}
