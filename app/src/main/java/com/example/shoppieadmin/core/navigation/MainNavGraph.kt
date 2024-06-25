package com.example.shoppieadmin.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieadmin.domain.auth.main.models.BottomBarScreen
import com.example.shoppieadmin.presentation.home.HomeScreen
import com.example.shoppieadmin.presentation.home.ProfileScreen
import com.example.shoppieadmin.presentation.home.StatsScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.MAIN
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen()
        }

        composable(BottomBarScreen.Stats.route) {
            StatsScreen()
        }

        composable(BottomBarScreen.Profile.route) {
            ProfileScreen()
        }

        detailsNavGraph(navController)
    }
}
