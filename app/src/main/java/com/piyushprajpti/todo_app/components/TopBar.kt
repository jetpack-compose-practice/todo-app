package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piyushprajpti.todo_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    image: Painter,
    accountClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            Icon(
                painter = image,
                contentDescription = "logo",
                tint = Color(0xFF2563EB),
                modifier = Modifier
                    .size(40.dp)
            )
        },
        title = {},
        actions = {
            IconButton(onClick = { accountClick() }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Account",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        },
        modifier = Modifier
            .padding(horizontal = 10.dp),

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}