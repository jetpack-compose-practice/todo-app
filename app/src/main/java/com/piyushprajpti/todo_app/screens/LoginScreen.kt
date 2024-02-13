package com.piyushprajpti.todo_app.screens

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.components.ActionButton
import com.piyushprajpti.todo_app.components.AlternateActionButton
import com.piyushprajpti.todo_app.components.ErrorField
import com.piyushprajpti.todo_app.components.InputField
import com.piyushprajpti.todo_app.components.URL
import com.piyushprajpti.todo_app.components.getDataStore
import com.piyushprajpti.database.UserData
import com.piyushprajpti.todo_app.ui.theme.GrayColor
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
data class LoginRequest(

    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String

)

@Serializable
data class LoginResponse(
    @SerialName("_id")
    val id: String = "",

    @SerialName("message")
    val error: String = ""
)

@Composable
fun LoginScreen(
    onSignupClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutine = rememberCoroutineScope()

    val context = LocalContext.current

    val userData = UserData(context.getDataStore)

    val client = HttpClient(Android) {
        expectSuccess = false
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val email = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val password = remember {
        mutableStateOf(TextFieldValue(""))
    }

    var data by remember {
        mutableStateOf(LoginResponse("", ""))
    }

    fun onSubmit() {
        coroutine.launch {

            try {
                val response: HttpResponse = client.post("${URL}login") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        LoginRequest(
                            email = email.value.text,
                            password = password.value.text
                        )
                    )
                }

                data = response.body<LoginResponse>()

            } catch (error: Exception) {
                LoginResponse(error = "Server Unreachable. Please try again.")
                Log.d("output", error.stackTraceToString())
            }

            if (data.error == "") {
                userData.setId(data.id)
                onLoginSuccess()
            }

        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Welcome Back",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

        Text(
            text = "Login to view your saved notes",
            color = GrayColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        InputField(
            value = email,
            logo = Icons.Default.Email,
            placeholder = "Email Address",
            keyboardType = KeyboardType.Email
        )
        InputField(
            value = password,
            logo = Icons.Default.Lock,
            placeholder = "Password",
            keyboardType = KeyboardType.Password
        )

        ErrorField(errorText = data.error)

        ActionButton(text = "Log In", clickAction = { onSubmit() })

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Don't have an account?",
            color = GrayColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        AlternateActionButton(
            text = "Sign Up Now",
            clickAction = { onSignupClick() }
        )
    }
}