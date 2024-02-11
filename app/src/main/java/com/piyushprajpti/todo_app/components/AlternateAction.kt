package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor

@Composable
fun AlternateAction(
    text1: String,
    text2: String,
    clickAction: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text1, color = GrayColor)

        TextButton(
            onClick = { clickAction() },
            contentPadding = PaddingValues(horizontal = 1.dp, vertical = 1.dp),
            modifier = Modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple()
                ) { clickAction() }
        ) {
            Text(
                text = text2,
                color = PrimaryColor,
                fontWeight = FontWeight.W700
            )
        }
    }
}