package com.piyushprajpti.todo_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.piyushprajpti.todo_app.screens.HomeScreen
import com.piyushprajpti.todo_app.screens.LoginScreen
import com.piyushprajpti.todo_app.screens.NoteScreen
import com.piyushprajpti.todo_app.screens.ProfileScreen
import com.piyushprajpti.todo_app.screens.SignUpScreen

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
                            Screen.NoteScreen.route + "?noteid=$noteid"
                        )
                    }
                )
            }

            composable(
                route = Screen.NoteScreen.route + "?noteid={noteid}",
                arguments = listOf(
                    navArgument("noteid") {
                        type = NavType.StringType
                        nullable = true
                    }
                )
            ) {
                NoteScreen(
                    onBackClick = { navController.popBackStack() },
                    onDeleteClick = { navController.popBackStack() },
                    onSaveClick = { navController.popBackStack() },
                    noteid = it.arguments?.getString("noteid")
                )
            }

            composable(route = Screen.ProfileScreen.route) {
                ProfileScreen(
                    onBackClick = { navController.navigate(Screen.HomeScreen.route) },
                    onLogoutClick = { navController.navigate("auth_graph") }
                )
            }
        }

    }
}