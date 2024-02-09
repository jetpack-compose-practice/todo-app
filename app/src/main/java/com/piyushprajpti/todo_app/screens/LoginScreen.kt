package com.piyushprajpti.todo_app.screens

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.components.ActionButton
import com.piyushprajpti.todo_app.components.AlternateAction
import com.piyushprajpti.todo_app.components.InputField
import com.piyushprajpti.todo_app.components.URL
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
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
    val error: String? = null
)

@Composable
fun LoginScreen(
    onSignupClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutine = rememberCoroutineScope()

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

    var data: LoginResponse

    fun onSubmit() {
        coroutine.launch {


            try {
                val response: HttpResponse = client.post("${URL}login") {
                    contentType(ContentType.Application.Json)
                    setBody(LoginRequest(email.value.text, password.value.text))
                }

                data = response.body<LoginResponse>()
                Log.d("output", data.toString())

            } catch (error: Exception) {
                Log.d("output", error.stackTraceToString())
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

        ActionButton(text = "Log In", clickAction = { onSubmit() })

        AlternateAction(
            text1 = "Don't have an Account?",
            text2 = "Sign Up Now",
            clickAction = { onSignupClick() }
        )
    }
}