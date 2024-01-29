package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    modifier: Modifier = Modifier
) {
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//            .clickable { }
//            .clip(RoundedCornerShape(10.dp))
//            .background(Color(0xFF2563EB), RoundedCornerShape(10.dp))
//            .padding(15.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Default.Add,
//            contentDescription = "Add note",
////            tint = Color.Black,
//            modifier = Modifier.size(30.dp)
//        )
//
//    }

    FloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
        .padding(10.dp)
            .size(65.dp),
        shape = RoundedCornerShape(12.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note",
                modifier = Modifier
                    .size(35.dp)
            )
        }
    )


}