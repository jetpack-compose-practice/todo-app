package com.piyushprajpti.todo_app.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piyushprajpti.todo_app.Screen
import com.piyushprajpti.todo_app.components.ActionButton
import com.piyushprajpti.todo_app.components.AlternateAction
import com.piyushprajpti.todo_app.components.InputField

@Composable
fun SignUpScreen(onLoginClick: () -> Unit, modifier: Modifier = Modifier) {


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
            logo = Icons.Default.Person,
            placeholder = "Your Name",
            keyboardType = KeyboardType.Text
        )
        InputField(
            logo = Icons.Default.Email,
            placeholder = "Email Address",
            keyboardType = KeyboardType.Email
        )
        InputField(
            logo = Icons.Default.Lock,
            placeholder = "Password",
            keyboardType = KeyboardType.Password
        )
        InputField(
            logo = Icons.Default.Lock,
            placeholder = "Confirm Password",
            keyboardType = KeyboardType.Password
        )

        ActionButton(text = "Sign Up", clickAction = {})

        AlternateAction(
            text1 = "Already have an Account?",
            text2 = "Login",
            clickAction = { onLoginClick() })
    }
}