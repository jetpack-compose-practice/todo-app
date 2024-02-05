package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.piyushprajpti.todo_app.Screen
import com.piyushprajpti.todo_app.ui.theme.DescDark
import com.piyushprajpti.todo_app.ui.theme.DescLight
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor
import com.piyushprajpti.todo_app.ui.theme.TitleDark
import com.piyushprajpti.todo_app.ui.theme.TitleLight

@Composable
fun NoteBox(
    onClick: () -> Unit,
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                color = PrimaryColor,
                width = 1.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(14.dp)
    ) {
        Text(
            text = title,
            color = if (isSystemInDarkTheme()) TitleDark else TitleLight,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            color = if (isSystemInDarkTheme()) DescDark else DescLight
        )
    }

}