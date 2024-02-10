package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.ui.theme.ErrorRed

@Composable
fun ErrorField(
    errorText: String
) {
    Text(
        text = errorText,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
        color = ErrorRed,
        fontSize = 14.sp
    )
}