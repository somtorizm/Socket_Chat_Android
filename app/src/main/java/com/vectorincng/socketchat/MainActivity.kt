package com.vectorincng.socketchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vectorincng.socketchat.ui.theme.screens.ChatScreen
import com.vectorincng.socketchat.ui.theme.SocketChatTheme
import com.vectorincng.socketchat.ui.theme.screens.CallScreen
import com.vectorincng.socketchat.ui.theme.screens.HomeScreen
import com.vectorincng.socketchat.ui.theme.screens.LoginScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SocketChatTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0xFF1C1B1B)
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("home") {
                            HomeScreen(navController = navController)
                        }
                        composable("chat") {
                            ChatScreen(navController = navController)
                        }
                        composable("camera") {

                        }
                        composable("call") {
                            CallScreen(navController = navController)
                        }
                        composable("login") {
                            LoginScreen(navController)
                        }
                    }
                }
            }
        }
    }
}