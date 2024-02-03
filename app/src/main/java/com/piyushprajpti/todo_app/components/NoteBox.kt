package com.piyushprajpti.todo_app.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor
import java.sql.Wrapper

@Composable
fun NoteBox() {
    Scaffold(
        topBar = { TopBar() }
    ) { paddingValue ->
        Divider(modifier = Modifier.padding(paddingValue), thickness = 0.5.dp)

        Wrapper(modifier = Modifier.padding(paddingValue))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),

        navigationIcon = {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
        },

        title = {},

        actions = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "delete",
                modifier = Modifier.padding(end = 25.dp)
            )
            Text(
                text = "Save",
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        },
        modifier = Modifier
            .padding(horizontal = 15.dp),
    )
}

@Composable
fun TextArea(
    placeholder: String,
    modifier: Modifier = Modifier
) {

    val inputValue = remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(

            value = inputValue.value,

            onValueChange = { inputValue.value = it },

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

@Composable
fun Wrapper(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TextArea(placeholder = "Title", modifier = Modifier)

        TextArea(placeholder = "Description", modifier = Modifier.height(400.dp))
    }
}