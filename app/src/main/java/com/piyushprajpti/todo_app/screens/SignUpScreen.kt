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
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piyushprajpti.todo_app.Screen
import com.piyushprajpti.todo_app.components.ActionButton
import com.piyushprajpti.todo_app.components.AlternateAction
import com.piyushprajpti.todo_app.components.ErrorField
import com.piyushprajpti.todo_app.components.InputField
import com.piyushprajpti.todo_app.components.URL
import com.piyushprajpti.todo_app.components.getDataStore
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
data class SignupRequest(

    @SerialName("name")
    val name: String,

    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String,

    @SerialName("confirmPassword")
    val confirmPassword: String

)

@Serializable
data class SignupResponse(
    @SerialName("_id")
    val id: String = "",

    @SerialName("message")
    val error: String = ""
)

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    onSignupSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {

    val coroutine = rememberCoroutineScope()

    val context = LocalContext.current
    context.getDataStore
    val client = HttpClient(Android) {
        expectSuccess = false
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val name = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val email = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val password = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val confirmPassword = remember {
        mutableStateOf(TextFieldValue(""))
    }

    var data by remember {
        mutableStateOf(SignupResponse("", ""))
    }

    fun onSubmit() {
        coroutine.launch {

            try {

                val response: HttpResponse = client.post("${URL}signup") {
                    contentType(ContentType.Application.Json)

                    setBody(
                        SignupRequest(
                            name = name.value.text,
                            email = email.value.text,
                            password = password.value.text,
                            confirmPassword = confirmPassword.value.text
                        )
                    )
                }

                data = response.body<SignupResponse>()

                Log.d("output", data.toString())

            } catch (error: Exception) {
                data = SignupResponse(error = "Server Unreachable. Please try again.")
                Log.d("output", error.stackTraceToString())
            }

            if (data.error == "") {
                onSignupSuccess()
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
            text = "Create New Account",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

        Spacer(modifier = Modifier.height(30.dp))

        InputField(
            value = name,
            logo = Icons.Default.Person,
            placeholder = "Your Name",
            keyboardType = KeyboardType.Text
        )

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
        InputField(
            value = confirmPassword,
            logo = Icons.Default.Lock,
            placeholder = "Confirm Password",
            keyboardType = KeyboardType.Password
        )

        ErrorField(errorText = data.error)

        ActionButton(text = "Sign Up", clickAction = { onSubmit() })

        AlternateAction(
            text1 = "Already have an Account?",
            text2 = "Login",
            clickAction = { onLoginClick() })
    }
}