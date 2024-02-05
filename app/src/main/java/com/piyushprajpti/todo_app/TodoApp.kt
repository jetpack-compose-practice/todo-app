package com.piyushprajpti.todo_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.piyushprajpti.todo_app.screens.HomeScreen
import com.piyushprajpti.todo_app.screens.LoginScreen
import com.piyushprajpti.todo_app.screens.NoteScreen
import com.piyushprajpti.todo_app.screens.ProfileScreen
import com.piyushprajpti.todo_app.screens.SignUpScreen
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Preview
@Composable
fun TodoApp() {
    val navController = rememberNavController()

    val client = HttpClient(Android)

    val url = "http://localhost:8080"

    LaunchedEffect(Unit) {

        val output = client.post(url) {
            header("Content-Type", "application/json")
//            setBody()
        }

    }





    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                onSignupClick = {
                    navController.navigate(Screen.SignupScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.SignupScreen.route) {
            SignUpScreen(
                onLoginClick = {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                onProfileClick = { navController.navigate(Screen.ProfileScreen.route) },
                onAddNoteButtonClick = {
                    navController.navigate(Screen.NoteScreen.route)
                },
                onNoteBoxClick = { title, description ->
                    navController.navigate(
                        Screen.NoteScreen.route + "?title=$title&description=$description"
                    )
                }
            )

        }

        composable(
            route = Screen.NoteScreen.route + "?title={title}&description={description}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true

                },
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            NoteScreen(
                navController = navController,
                title = it.arguments?.getString("title"),
                description = it.arguments?.getString("description")
            )
        }

        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(
                onBackClick = { navController.navigate(Screen.HomeScreen.route) },
                name = "Piyush Prajapati",
                email = "piyushoa2004@gmail.com"
            )
        }
    }
}