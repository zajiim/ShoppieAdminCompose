package com.example.shoppieadmin.core.navigation

sealed class Routes(
    val route: String
) {
    data object LoginScreen: Routes(route = "login_screen")
    data object HomeScreen: Routes(route = "home_screen")
    data object AuthNavigation: Routes("auth_navigation")
    data object HomeNavigation: Routes("home_navigation")
}
