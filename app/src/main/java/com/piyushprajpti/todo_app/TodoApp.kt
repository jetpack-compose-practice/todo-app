package com.piyushprajpti.todo_app

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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

@Preview
@Composable
fun TodoApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                onSignupClick = {
                    navController.navigate(Screen.SignupScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                onLoginSuccess = {navController.navigate(Screen.HomeScreen.route)}
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
                },
                onSignupSuccess = {navController.navigate(Screen.HomeScreen.route)}
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