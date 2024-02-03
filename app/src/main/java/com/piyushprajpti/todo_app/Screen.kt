package com.piyushprajpti.todo_app

sealed class Screen (val route: String) {
    data object SignupScreen: Screen("signup")
    data object LoginScreen: Screen("login")
    data object HomeScreen: Screen("home")
}