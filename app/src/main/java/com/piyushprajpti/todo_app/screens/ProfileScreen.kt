package com.piyushprajpti.todo_app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.database.NotesDB
import com.piyushprajpti.todo_app.components.AlternateActionButton
import com.piyushprajpti.todo_app.components.URL
import com.piyushprajpti.todo_app.components.getDataStore
import com.piyushprajpti.database.UserData
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor
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
data class UserId(
    @SerialName("userid")
    val userId: String
)

@Serializable
data class UserInfo(
    @SerialName("name")
    val name: String,

    @SerialName("email")
    val email: String
)

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val coroutine = rememberCoroutineScope()

    val client = HttpClient(Android) {
        expectSuccess = false
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val context = LocalContext.current
    val userData = UserData(context.getDataStore)

    val userid = userData.getId().collectAsState(initial = "")

    fun deleteUserId() {
        coroutine.launch { userData.setId("") }
    }

    val data = remember {
        mutableStateOf(UserInfo("", ""))
    }

    LaunchedEffect(Unit) {
        coroutine.launch {

            try {

                val response: HttpResponse = client.post("${URL}profilepage") {
                    contentType(ContentType.Application.Json)
                    setBody(UserId(userid.value))
                }

                data.value = response.body<UserInfo>()

            } catch (error: Exception) {
                Log.d("output", "ProfileScreen: ${error.stackTraceToString()}")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        DetailWrapper(input = data.value.name, logo = Icons.Outlined.Person)

        Spacer(modifier = Modifier.height(10.dp))

        DetailWrapper(input = data.value.email, logo = Icons.Outlined.Email)

        AlternateActionButton(
            text = "Log Out", clickAction = {
                deleteUserId()
                onLogoutClick()
            }
        )
    }
}

@Composable
fun DetailWrapper(
    input: String,
    logo: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {

        Icon(
            imageVector = logo,
            contentDescription = "logo",
            tint = PrimaryColor,
            modifier = Modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = input,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun Preview() {
    Box(modifier = Modifier.background(Color(0xFFF3F3F3))) {
        ProfileScreen(
            onBackClick = { /*TODO*/ },
            onLogoutClick = {},
        )
    }
}