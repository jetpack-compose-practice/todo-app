package com.piyushprajpti.todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.piyushprajpti.todo_app.components.NoteBox
import com.piyushprajpti.todo_app.screens.HomeScreen
import com.piyushprajpti.todo_app.screens.LoginScreen
import com.piyushprajpti.todo_app.ui.theme.TodoappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoappTheme {
                Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    NoteBox()
                }
            }
        }
    }
}