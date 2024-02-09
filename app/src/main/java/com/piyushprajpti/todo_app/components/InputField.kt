package com.piyushprajpti.todo_app.components

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor

@Composable
fun InputField(
    value: MutableState<TextFieldValue>,
    logo: ImageVector,
    placeholder: String,
    keyboardType: KeyboardType
) {

    val isPswdVisible = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            singleLine = true,

            value = value.value,

            onValueChange = { value.value = it },

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

            leadingIcon = {
                Icon(
                    imageVector = logo,
                    contentDescription = "logo",
                    tint = PrimaryColor,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 0.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 10.dp
                            ),
                            color = PrimaryColor
                        )
                        .padding(horizontal = 15.dp)
                        .height(50.dp)
                )
            },

            trailingIcon = {
                if (keyboardType == KeyboardType.Password) {
                    val icon =
                        if (isPswdVisible.value) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff

                    IconButton(onClick = { isPswdVisible.value = !isPswdVisible.value }) {
                        Icon(imageVector = icon, contentDescription = "Show Password")
                    }
                }
            },

            visualTransformation = if (keyboardType == KeyboardType.Password) {
                if (isPswdVisible.value) VisualTransformation.None
                else PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },

            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),

            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.CenterVertically)
        )

    }
}