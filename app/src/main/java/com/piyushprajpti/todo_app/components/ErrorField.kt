package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorField(
    errorText: String
) {
    Text(
        text = errorText,
        modifier = Modifier
            .height(16.dp)
            .fillMaxWidth(),
        color = Color.Red
    )
}