package com.piyushprajpti.todo_app.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.piyushprajpti.todo_app.Screen
import com.piyushprajpti.todo_app.ui.theme.GrayColor
import com.piyushprajpti.todo_app.ui.theme.PrimaryColor

@Composable
fun NoteScreen(
    navController: NavController,
    title: String?,
    description: String?
) {
    Scaffold(
        topBar = {
            NoteScreenTopBar(
                onBackClick = { navController.navigate(Screen.HomeScreen.route) },
                onDeleteClick = { /*TODO*/ },
                onSaveClick = {})
        }
    ) { paddingValue ->

        Divider(modifier = Modifier.padding(paddingValue), thickness = 0.5.dp)

//        Wrapper(modifier = Modifier.padding(paddingValue))

        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {

            TextArea(placeholder = "Title", input = ""+title, modifier = Modifier)

            TextArea(
                placeholder = "Description",
                input = ""+description,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreenTopBar(
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit
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
                onClick = { onDeleteClick() },
                modifier = Modifier.padding(end = 25.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delete",
                )
            }

            TextButton(onClick = { onSaveClick() }) {
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
    input: String,
    modifier: Modifier = Modifier
) {

    val inputValue = remember {
        mutableStateOf(input)
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