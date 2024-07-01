package com.vectorincng.socketchat.extensions


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    color: Color,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(),
    ) {
        content()
    }
}

@Composable
fun EmailTextField(
    emailState: MutableState<String>
) {
    Card(
        onClick = { }, modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        TextField(
            shape = RoundedCornerShape(10.dp),
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun VerticalSpacer(value: Int) {
    Spacer(Modifier.height(value.dp))
}