package com.piyushprajpti.todo_app.screens

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.database.Note
import com.piyushprajpti.database.NotesDB
import com.piyushprajpti.database.UserData
import com.piyushprajpti.todo_app.components.URL
import com.piyushprajpti.todo_app.components.getDataStore
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class NoteData(
    @SerialName("userid")
    val userid: String,

    @SerialName("noteid")
    val noteid: String,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String
)

@Serializable
data class NoteId(
    @SerialName("_id") val noteid: String
)

@Composable
fun NoteScreen(
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    noteid: String?
) {

    val coroutine = rememberCoroutineScope()

    val context = LocalContext.current
    val userData = UserData(context.getDataStore)
    val userid = userData.getId().collectAsState(initial = "")

    val noteDao = NotesDB.getDatabase(context).noteDao()

    val client = HttpClient(Android) {
        expectSuccess = false
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }


    val title = remember {
        mutableStateOf(TextFieldValue())
    }

    val description = remember {
        mutableStateOf(TextFieldValue())
    }

    if (noteid == null) {
        title.value = TextFieldValue("")
        description.value = TextFieldValue("")
    } else {
        val noteDetails =
            noteDao.getNoteDetail(noteid).collectAsState(initial = Note("", "", "", ""))

        title.value = TextFieldValue(noteDetails.value.title)
        description.value = TextFieldValue(noteDetails.value.description)

    }

    LaunchedEffect(Unit) {
        coroutine.launch {
            try {
                val response: HttpResponse = client.post("${URL}fetchanote") {
                    contentType(ContentType.Application.Json)
                    setBody { NoteId(noteid = noteid.toString()) }

                }

                Log.d("output", "NoteScreen: noteidresponse: ${response.bodyAsText()}")

            } catch (error: Exception) {
                Log.d("output", "NoteScreen: launchedeffecterror:  ${error.stackTraceToString()}")
            }
        }
    }

    fun onDelete() {
        coroutine.launch {
            try {
                val response: HttpResponse = client.post("${URL}deletenote") {
                    contentType(ContentType.Application.Json)
                    setBody { { noteid } }
                }

                Log.d("output", "onDelete: ${response.bodyAsText()}")

                onDeleteClick()

            } catch (error: Exception) {
                Log.d("output", "onDeleteError: ${error.stackTraceToString()}")
            }
        }
    }

    fun onSave() {
        coroutine.launch {

            Log.d("output", "onSave: noteid: $noteid")
            try {
                val response: HttpResponse = client.post("${URL}addnote") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        NoteData(
                            userid = userid.value,
                            noteid = noteid ?: "",
                            title = title.value.text,
                            description = description.value.text
                        )
                    )
                }

                Log.d("output", "onSaveError: ${response.bodyAsText()}")

                onSaveClick()


            } catch (error: Exception) {
                Log.d("output", error.stackTraceToString())
            }
        }
    }

    Scaffold(
        topBar = {
            NoteScreenTopBar(
                onBackClick = { onBackClick() },
                onDelete = { onDelete() },
                onSave = { onSave() }
            )
        }
    ) { paddingValue ->

        Divider(modifier = Modifier.padding(paddingValue), thickness = 0.5.dp)

        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {

            TextArea(
                placeholder = "Title",
                input = title,
                modifier = Modifier
            )

            TextArea(
                placeholder = "Description",
                input = description,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreenTopBar(
    onBackClick: () -> Unit,
    onDelete: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),

        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
        },

        title = {},

        actions = {

            IconButton(
                onClick = { onDelete() },
                modifier = Modifier.padding(end = 25.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delete",
                )
            }

            TextButton(onClick = { onSave() }) {
                Text(
                    text = "Save",
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }
    )
}

@Composable
fun TextArea(
    placeholder: String,
    input: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(

            value = input.value,

            onValueChange = { input.value = it },

            placeholder = { Text(text = placeholder) },

            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                unfocusedBorderColor = GrayColor
            ),

            shape = RoundedCornerShape(
                10.dp
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

            modifier = modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        )

    }
}