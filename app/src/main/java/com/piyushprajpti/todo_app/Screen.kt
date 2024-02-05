package com.piyushprajpti.todo_app

sealed class Screen(val route: String) {
    data object SignupScreen : Screen("signup")
    data object LoginScreen : Screen("login")
    data object HomeScreen : Screen("home")
    data object NoteScreen : Screen("noteScreen")
    data object NoteBox : Screen("notebox")
    data object ProfileScreen : Screen("profilescreen")

//    fun withArgs(vararg args: String?): String {
//        return buildString {
//            append(route)
//            args.forEach { arg ->
//                append("{$arg}")
//            }
//        }
//    }
}