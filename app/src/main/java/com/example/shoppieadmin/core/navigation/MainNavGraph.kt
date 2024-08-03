package com.example.shoppieadmin.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieadmin.domain.main.models.BottomBarScreen
import com.example.shoppieadmin.presentation.home.HomeScreen
import com.example.shoppieadmin.presentation.home.ProfileScreen
import com.example.shoppieadmin.presentation.home.StatsScreen
import com.example.shoppieadmin.presentation.home.add_products.AddProductsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController, bottomPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.MAIN
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                bottomPadding = bottomPadding
            ) {
                navController.navigate(DetailsScreenRoutes.AddProducts.route) {
                    composable(route = DetailsScreenRoutes.AddProducts.route) {
                        AddProductsScreen(
                            navController = navController, bottomPadding = bottomPadding
                        )
                    }
                }
            }
        }

        composable(BottomBarScreen.Stats.route) {
            StatsScreen()
        }

        composable(BottomBarScreen.Profile.route) {
            ProfileScreen()
        }

        detailsNavGraph(
            navController = navController, bottomPadding = bottomPadding
        )
    }
}
