package com.vectorincng.socketchat.ui.theme.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vectorincng.socketchat.R
import com.vectorincng.socketchat.extensions.EmailTextField
import com.vectorincng.socketchat.extensions.LoginButton
import com.vectorincng.socketchat.extensions.VerticalSpacer

@ExperimentalAnimationApi
@Composable
fun LoginScreen(navController: NavHostController) {
    var visibility by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val config = LocalConfiguration.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            AnimatedVisibility(
                visible = visibility,
                enter = slideInHorizontally(
                    initialOffsetX = {
                        with(density) { config.screenWidthDp.dp.roundToPx() * -1 }
                    },
                    animationSpec = tween(1200)
                )

            ) {
            }

            LoginForm(
                visibility,
                density,
                config.screenWidthDp,
                navController
            )
        }
        LaunchedEffect(key1 = "key1", block = { visibility = true })
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
    visibility: Boolean,
    density: Density,
    screenWidth: Int,
    navController: NavHostController
) {

    val slideInHorizontallyFromLeftAnimation = slideInHorizontally(
        initialOffsetX = {
            with(density) { screenWidth.dp.roundToPx() * -1 }
        },
        animationSpec = tween(1200)
    )
    val slideInHorizontallyFromRightAnimation = slideInHorizontally(
        initialOffsetX = {
            with(density) { screenWidth.dp.roundToPx() }
        },
        animationSpec = tween(1200)
    )
    val expandInFromCenterAnimation =
        expandIn(animationSpec = tween(1200), expandFrom = Alignment.Center)
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

        AnimatedVisibility(
            visible = visibility,
            enter = slideInHorizontallyFromLeftAnimation,
            content = { EmailTextField() }
        )

        VerticalSpacer(value = 16)

        AnimatedVisibility(
            visible = visibility,
            enter = expandInFromCenterAnimation
        ) {
            LoginButton(onClick = { navController.navigate("home")}, color = Color.Blue) {
                Text(
                    "Log in", color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        VerticalSpacer(value = 24)
        }
}