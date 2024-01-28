package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piyushprajpti.todo_app.R

@Composable
fun TopBar(
    image: Painter,
    accountClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {

        Icon(
            painter = image,
            contentDescription = "logo",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(40.dp)

        )

        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "Account",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .clickable { accountClick() }
                .size(40.dp)
        )
    }
    Divider(thickness = 0.5.dp)
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TopBar(image = painterResource(id = R.drawable.logo)) {
        
    }
}