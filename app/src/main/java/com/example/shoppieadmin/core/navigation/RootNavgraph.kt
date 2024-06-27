package com.example.shoppieadmin.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppieadmin.presentation.auth.main.MainActivityViewModel
import com.example.shoppieadmin.presentation.auth.main.MainScreen


@Composable
fun RootNavGraph(viewModel: MainActivityViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = viewModel.startDestination
    ) {
        authNavGraph(navController)

        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}