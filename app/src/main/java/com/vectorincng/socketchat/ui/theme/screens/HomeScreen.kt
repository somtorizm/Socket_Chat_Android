package com.vectorincng.socketchat.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vectorincng.socketchat.data.listOfConversations
import com.vectorincng.socketchat.ui.theme.components.ConversationItem
import com.vectorincng.socketchat.ui.theme.components.HomeAppBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            HomeAppBar()
        },
        containerColor = Color.Transparent,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            items(listOfConversations) { conversation ->
                ConversationItem(
                    conversation = conversation,
                    onClick = {
                        navController.navigate("chat")
                    }
                )
            }
        }
    }
}