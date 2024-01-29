package com.piyushprajpti.todo_app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piyushprajpti.todo_app.components.AddNoteButton
import com.piyushprajpti.todo_app.components.TopBar

@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopBar(image = painterResource(id = R.drawable.logo)) {} },

        floatingActionButton = { AddNoteButton() },

        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.background(Color.Green)
    ) { paddingValue ->
        Divider(thickness = .5.dp, modifier = Modifier.padding(paddingValue))
    }
}