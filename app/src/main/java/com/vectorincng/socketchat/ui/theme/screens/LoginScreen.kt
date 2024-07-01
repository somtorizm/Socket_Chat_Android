package com.vectorincng.socketchat.ui.theme.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vectorincng.socketchat.R
import com.vectorincng.socketchat.extensions.EmailTextField
import com.vectorincng.socketchat.extensions.LoginButton
import com.vectorincng.socketchat.extensions.VerticalSpacer
import com.vectorincng.socketchat.presentation.ClientViewModel
import com.vectorincng.socketchat.utils.State

@ExperimentalAnimationApi
@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<ClientViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val emailState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Center
    ) {

        when(state) {
            is State.Failed -> Unit
            is State.Loading -> LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            is State.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate("chat")
                }
            }
        }

        LoginForm(
            emailState = emailState,
            onClick = {
                viewModel.login(emailState.value)
            }
        )
    }
}

@Composable
fun LoginAppBar(modifier: Modifier) {
    Box(
        modifier
    ) {
        Text(
            "Socket Chat Login",
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                color = Color(0xFFCCCCCC),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sen))
            )
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun LoginForm(
    emailState: MutableState<String>,
    onClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        LoginAppBar(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        )

        VerticalSpacer(value = 16)

        EmailTextField(
            emailState = emailState
        )

        VerticalSpacer(value = 16)

        LoginButton(onClick = { onClick() }, color = Color.Blue) {
            Text(
                "Log in",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        VerticalSpacer(value = 24)
    }
}
