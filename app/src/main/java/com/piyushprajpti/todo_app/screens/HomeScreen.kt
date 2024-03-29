package com.piyushprajpti.todo_app.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.piyushprajpti.database.Note
import com.piyushprajpti.database.NotesDB
import com.piyushprajpti.todo_app.R
import com.piyushprajpti.todo_app.components.AddNoteButton
import com.piyushprajpti.todo_app.components.DefaultHomeScreenMsg
import com.piyushprajpti.todo_app.components.NoteBox
import com.piyushprajpti.todo_app.components.HomeScreenTopBar
import com.piyushprajpti.todo_app.components.URL
import com.piyushprajpti.todo_app.components.getDataStore
import com.piyushprajpti.database.UserData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class RequestData(
    @SerialName("userid")
    val userId: String
)
//
//@Serializable
//data class Note(
//
//    @SerialName("_id")
//    val noteid: String,
//
//    @SerialName("title")
//    val title: String,
//
//    @SerialName("description")
//    val description: String
//)

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onAddNoteButtonClick: () -> Unit,
    onNoteBoxClick: (noteid: String) -> Unit,
) {
    val coroutine = rememberCoroutineScope()

    val client = HttpClient(Android) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val context = LocalContext.current
    val userData = UserData(context.getDataStore)
    val userId = userData.getId().collectAsState(initial = "")


    val notesList = remember {
        mutableStateOf<List<Note>>(emptyList())
    }

    LaunchedEffect(Unit) {
        coroutine.launch {
            try {

                val response: HttpResponse = client.post(URL) {
                    contentType(ContentType.Application.Json)

                    setBody(RequestData(userId = userId.value))
                }

                notesList.value = response.body<List<Note>>()

//                noteDao.insertAllNotes(notesList.value)

            } catch (error: Exception) {
                Log.d("output", "HOME SCREEN ERROR: ${error.stackTraceToString()}")
            }
        }

    }

//    val notes = noteDao.getAllNotes().collectAsState(initial = emptyList())

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

        LazyColumn(
            modifier = Modifier
                .padding(paddingValue)
                .padding(horizontal = 10.dp)
        ) {
            item {
                if (notesList.value.isEmpty()) {
                    DefaultHomeScreenMsg(modifier = Modifier.padding(paddingValue))
                } else {
                    notesList.value.forEach { note ->
                        NoteBox(
                            onClick = { onNoteBoxClick(note.noteid) },
                            noteid = note.noteid,
                            title = note.title,
                            description = note.description
                        )
                    }
                }
            }
        }
    }
}