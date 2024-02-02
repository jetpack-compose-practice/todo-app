package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor

@Composable
fun AlternateAction(
    text1: String,
    text2: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text1, color = GrayColor, modifier = Modifier.padding(bottom = 5.dp))
        Text(text = text2, color = PrimaryColor, fontWeight = FontWeight.W700, fontSize = 18.sp)
    }
}