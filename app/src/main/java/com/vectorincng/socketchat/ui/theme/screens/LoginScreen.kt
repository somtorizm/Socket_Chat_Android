package com.vectorincng.socketchat.ui.theme.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vectorincng.socketchat.extensions.EmailTextField
import com.vectorincng.socketchat.extensions.LoginButton
import com.vectorincng.socketchat.extensions.PasswordTextField
import com.vectorincng.socketchat.extensions.VerticalSpacer
import com.vectorincng.socketchat.extensions.signUpText

@ExperimentalAnimationApi
@Composable
fun LoginScreen() {
    var visibility by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val config = LocalConfiguration.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .statusBarsPadding()
        ) {
            AnimatedVisibility(
                visible = visibility,
                enter = slideInHorizontally(
                    initialOffsetX = {
                        with(density) { config.screenWidthDp.dp.roundToPx() * -1 }
                    },
                    animationSpec = tween(1200)
                )

            ) {
                LoginAppBar(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                )
            }

            LoginForm(
                visibility,
                density,
                config.screenWidthDp
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
            "Log in",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 18.sp
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun LoginForm(visibility: Boolean, density: Density, screenWidth: Int) {

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
            .padding(horizontal = 24.dp)
            .absolutePadding(top = 16.dp)
    ) {

        AnimatedVisibility(
            visible = visibility,
            enter = slideInHorizontallyFromLeftAnimation,
            content = { EmailTextField() }
        )
        VerticalSpacer(value = 16)

        AnimatedVisibility(
            visible = visibility,
            enter = slideInHorizontallyFromRightAnimation,
            content = { PasswordTextField() }
        )


        VerticalSpacer(value = 16)

        AnimatedVisibility(
            visible = visibility,
            enter = expandInFromCenterAnimation,
            content = {
                Text(
                    "Forgot Password?",
                    fontSize = 15.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.SemiBold
                )
            }
        )
        VerticalSpacer(value = 32)

        AnimatedVisibility(
            visible = visibility,
            enter = expandInFromCenterAnimation
        ) {
            LoginButton(onClick = { }, color = Color.Blue) {
                Text(
                    "Log in", color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        VerticalSpacer(value = 24)
        AnimatedVisibility(
            visible = visibility,
            enter = expandInFromCenterAnimation
        ) {
            ClickableText(
                text = signUpText(),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp,
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                // perform action when text clicked
            }
        }
    }
}