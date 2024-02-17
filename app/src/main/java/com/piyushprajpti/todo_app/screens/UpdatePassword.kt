package com.piyushprajpti.todo_app.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.piyushprajpti.database.UserData
import com.piyushprajpti.todo_app.components.ActionButton
import com.piyushprajpti.todo_app.components.AlternateActionButton
import com.piyushprajpti.todo_app.components.ErrorField
import com.piyushprajpti.todo_app.components.InputField
import com.piyushprajpti.todo_app.components.URL
import com.piyushprajpti.todo_app.components.getDataStore
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
data class UpdateRequestData(
    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String,

    @SerialName("confirmPassword")
    val confirmPassword: String
)

@Serializable
data class UpdateResponse(
    @SerialName("message")
    val error: String = ""
)

@Composable
fun UpdatePassword(
    onLoginClick: () -> Unit,
) {
    val coroutine = rememberCoroutineScope()

    val context = LocalContext.current
    val userData = UserData(context.getDataStore)
    val userEmail = userData.getEmail().collectAsState(initial = "")

    val client = HttpClient(Android) {
        expectSuccess = false
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    var isLoading by remember { mutableStateOf(false) }

    val password = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val confirmPassword = remember {
        mutableStateOf(TextFieldValue(""))
    }

    var data by remember {
        mutableStateOf(UpdateResponse(""))
    }

    if (data.error.isNotEmpty()) isLoading = false

    fun onSave() {
        isLoading = true

        coroutine.launch {
            try {
                val response: HttpResponse = client.post("${URL}updatepassword") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        UpdateRequestData(
                            email = userEmail.value,
                            password = password.value.text,
                            confirmPassword = confirmPassword.value.text
                        )
                    )
                }

                data = response.body<UpdateResponse>()

            } catch (error: Exception) {
                Log.d("output", "onSave: ${error.stackTraceToString()}")
            }

            if (data.error.isEmpty()) {
                Toast.makeText(
                    context,
                    "Password updated successfully. Login to continue",
                    Toast.LENGTH_LONG
                ).show()

                onLoginClick()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Create New Password",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

        Text(
            text = "Enter new password for your account.",
            color = GrayColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        InputField(
            value = password,
            logo = Icons.Default.Lock,
            placeholder = "New Password",
            keyboardType = KeyboardType.Password
        )

        InputField(
            value = confirmPassword,
            logo = Icons.Default.Lock,
            placeholder = "Confirm New Password",
            keyboardType = KeyboardType.Password
        )

        ErrorField(errorText = data.error)

        ActionButton(text = "Save", isLoading = isLoading, clickAction = { onSave() })

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Remember your password?",
            color = GrayColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        AlternateActionButton(
            text = "Log In",
            clickAction = { onLoginClick() }
        )
    }
}