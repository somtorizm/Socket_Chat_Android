package com.vectorincng.socketchat.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vectorincng.socketchat.utils.ConnectionState
import com.vectorincng.socketchat.utils.connectivityState
import com.vectorincng.socketchat.R
import kotlinx.coroutines.delay

@Composable
fun ConnectivityStatus(isConnecting: Boolean) {
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    var visibility by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(isConnected = isConnected, isConnecting)
    }

    LaunchedEffect(isConnected) {
        if (!isConnected) {
            visibility = true
        } else {
            delay(2000)
            visibility = false
        }
    }
}

@Composable
fun ConnectivityStatusBox(isConnected: Boolean, isConnecting: Boolean) {
    val backgroundColor by animateColorAsState(if (isConnected) Color.Green else Color.Red)

    val message = when {
        isConnecting -> "Connecting"
        isConnected -> "Back Online!"
        else -> "No Internet Connection!"
    }

    val iconResource = when {
        isConnecting -> R.drawable.baseline_connecting_airports_24
        isConnected -> R.drawable.baseline_filter_vintage_24
        else -> android.R.drawable.radiobutton_off_background
    }

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(8.dp), Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painterResource(id = iconResource), "Connectivity Icon", tint = Color.Black)
            Spacer(modifier = Modifier.size(8.dp))
            Text(message, color = Color.Black, fontSize = 15.sp)
        }
    }
}