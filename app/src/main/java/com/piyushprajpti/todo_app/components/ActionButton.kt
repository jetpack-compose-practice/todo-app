package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor

@Composable
fun ActionButton(
    text: String
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp,
        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryColor)
            .padding(vertical = 10.dp)
            .clickable {  }
    )
}