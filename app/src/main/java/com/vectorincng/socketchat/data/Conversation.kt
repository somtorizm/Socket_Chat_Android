package com.vectorincng.socketchat.data

import com.vectorincng.socketchat.R


data class Conversation(
    val sender: String,
    val image: Int,
    val amILastSender: Boolean,
    val message: String,
    val time: String,
    val unread: Boolean,
)

val listOfConversations = listOf(
    Conversation(
        sender = "Janet Fowler",
        image = R.drawable.ic_launcher_foreground,
        amILastSender = false,
        message = "I'm going to San Francisco for a few days. I'll be back on Monday.",
        time = "now",
        unread = true,
    ),
    Conversation(
        sender = "Jason Boyd",
        image = R.drawable.ic_launcher_foreground,
        amILastSender = true,
        message = "Sounds good!",
        time = "16:23",
        unread = false,
    ),
    Conversation(
        sender = "Nicolas Dunn",
        image = R.drawable.ic_launcher_foreground,
        amILastSender = false,
        message = "See you there.",
        time = "16:22",
        unread = true,
    ),
    Conversation(
        sender = "Carol Clark",
        image = R.drawable.ic_launcher_foreground,
        amILastSender = false,
        message = "Are you going to the party tonight?",
        time = "15:30",
        unread = false,
    ),
    Conversation(
        sender = "Ann Carroll",
        image = R.drawable.ic_launcher_foreground,
        amILastSender = false,
        message = "Dinner tonight?",
        time = "Mon",
        unread = false,
    )
)