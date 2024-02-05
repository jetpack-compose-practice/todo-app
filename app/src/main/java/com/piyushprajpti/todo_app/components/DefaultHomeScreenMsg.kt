package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor

@Composable
fun DefaultHomeScreenMsg(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = GrayColor)) {
                append("No notes available. Add a new note using the")
            }
            withStyle(
                style = SpanStyle(
                    color = PrimaryColor,
                    fontSize = 28.sp,
                )
            ) {
                append(" + ")
            }
            withStyle(style = SpanStyle(color = GrayColor)) {
                append("button below.")
            }
        }

        Text(
            text = annotatedString,
            color = GrayColor,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 222.dp)
        )
    }
}