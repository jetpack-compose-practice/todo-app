package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddNoteButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = Modifier
            .padding(15.dp)
            .size(60.dp),
        shape = RoundedCornerShape(12.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note",
                tint = if (isSystemInDarkTheme()) Color.Black else Color.White,
                modifier = Modifier
                    .size(35.dp)
            )
        }
    )


}