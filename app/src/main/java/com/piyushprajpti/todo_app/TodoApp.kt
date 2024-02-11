package com.piyushprajpti.todo_app

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.piyushprajpti.todo_app.components.getDataStore
import com.piyushprajpti.todo_app.screens.HomeScreen
import com.piyushprajpti.todo_app.screens.LoginScreen
import com.piyushprajpti.todo_app.screens.NoteScreen
import com.piyushprajpti.todo_app.screens.ProfileScreen
import com.piyushprajpti.todo_app.screens.SignUpScreen
import com.piyushprajpti.todo_app.storage.UserData
import kotlinx.coroutines.flow.collect

@Preview
@Composable
fun TodoApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth_graph") {

        navigation(route = "auth_graph", startDestination = Screen.LoginScreen.route) {

            composable(route = Screen.LoginScreen.route) {
                LoginScreen(
                    onSignupClick = {
                        navController.navigate(Screen.SignupScreen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    },
                    onLoginSuccess = {
                        navController.navigate("home_graph") {
                            popUpTo("auth_graph") {
                                inclusive = false
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
                    },
                    onSignupSuccess = {
                        navController.navigate("home_graph") {
                            popUpTo("auth_graph") {
                                inclusive = false
                            }
                        }
                    }
                )
            }
        }

        navigation(route = "home_graph", startDestination = Screen.HomeScreen.route) {

            composable(route = Screen.HomeScreen.route) {
                HomeScreen(
                    onProfileClick = { navController.navigate(Screen.ProfileScreen.route) },
                    onAddNoteButtonClick = {
                        navController.navigate(Screen.NoteScreen.route)
                    },
                    onNoteBoxClick = { noteid ->
                        navController.navigate(
                            Screen.NoteScreen.route + "?title=$noteid"
                        )
                    }
                )
            }

            composable(
                route = Screen.NoteScreen.route + "?title={noteid}",
                arguments = listOf(
                    navArgument("noteid") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = true

                    }
                )
            ) {
                NoteScreen(
                    onBackClick = { navController.navigate(Screen.HomeScreen.route) },
                    noteid = it.arguments?.getString("noteid")
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
}