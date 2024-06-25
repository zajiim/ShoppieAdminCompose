package com.example.shoppieadmin.core.navigation



sealed class AuthScreen(val route: String) {
    data object Login: AuthScreen("login")
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
    const val ADD_PRODUCT = "add_product_graph"
}