package com.piyushprajpti.todo_app.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piyushprajpti.todo_app.R
import com.piyushprajpti.todo_app.components.AddNoteButton
import com.piyushprajpti.todo_app.components.TopBar

@Preview
@Composable
fun HomeScreen() {
    Scaffold(

        topBar = { TopBar(image = painterResource(id = R.drawable.logo)) {} },

        floatingActionButton = { AddNoteButton() },

        floatingActionButtonPosition = FabPosition.End,

    ) { paddingValue ->
        Divider(thickness = .5.dp, modifier = Modifier.padding(paddingValue))
        LoginScreen(modifier = Modifier.padding(paddingValue))
    }
}