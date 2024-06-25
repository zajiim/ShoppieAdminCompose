package com.example.shoppieadmin.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.shoppieadmin.presentation.auth.login.LoginScreen
import com.example.shoppieadmin.presentation.auth.main.MainActivityViewModel
import com.example.shoppieadmin.presentation.auth.main.MainScreen
import com.example.shoppieadmin.presentation.home.HomeScreen
import okhttp3.Route

//@Composable
//fun ShoppieNavGraph(
//    startDestination: String,
//    modifier: Modifier,
//    ) {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = startDestination) {
//        navigation(
//            route = Routes.AuthNavigation.route,
//            startDestination = Routes.LoginScreen.route
//        ) {
//            composable(Routes.LoginScreen.route) {
//                LoginScreen(modifier)
//            }
//        }
//
//        navigation(
//            route = Routes.HomeNavigation.route,
//            startDestination = Routes.HomeScreen.route
//        ) {
//            composable(route = Routes.HomeScreen.route) {
//                HomeScreen(modifier)
//            }
//        }
//    }
//
//}

@Composable
fun RootNavGraph(route: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Graph.AUTH,
//        route = Graph.ROOT,
        route = route
    ) {
        authNavGraph(navController)

        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }

}