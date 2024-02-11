package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor

@Composable
fun AlternateActionButton(
    text: String,
    clickAction: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(
            onClick = { clickAction() }
        ) {
            Text(
                text = text,
                color = PrimaryColor,
                fontWeight = FontWeight.W700,
                fontSize = 18.sp
            )
        }
    }
}