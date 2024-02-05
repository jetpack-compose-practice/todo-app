package com.piyushprajpti.todo_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piyushprajpti.todo_app.R
import com.piyushprajpti.todo_app.Screen
import com.piyushprajpti.todo_app.components.AddNoteButton
import com.piyushprajpti.todo_app.components.NoteBox
import com.piyushprajpti.todo_app.components.HomeScreenTopBar

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onAddNoteButtonClick: () -> Unit,
    onNoteBoxClick: (title: String, description: String) -> Unit,
) {


    Scaffold(

        topBar = {
            HomeScreenTopBar(
                image = painterResource(id = R.drawable.logo),
                onProfileClick = { onProfileClick() }
            )
        },

        floatingActionButton = {
            AddNoteButton(
                onClick = { onAddNoteButtonClick() }
            )
        },

        floatingActionButtonPosition = FabPosition.End,

        ) { paddingValue ->

        Divider(thickness = .5.dp, modifier = Modifier.padding(paddingValue))

//            DefaultHomeScreenMsg(modifier = Modifier.padding(paddingValue))

        Column(
            modifier = Modifier
                .padding(paddingValue)
                .padding(horizontal = 10.dp)
        ) {
            NoteBox(
                onClick = { onNoteBoxClick("", "") },
                title = "Piyush Prajapati",
                description = "Something Something Something Something Something Something SomethingSomethingSomething"
            )

        }
    }
}