package com.example.shoppieadmin.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shoppieadmin.presentation.home.add_products.AddProductsScreen

fun NavGraphBuilder.detailsNavGraph(
    navController: NavHostController,
    bottomPadding: PaddingValues
) {
    navigation(
        route = Graph.ADD_PRODUCT,
        startDestination = DetailsScreenRoutes.AddProducts.route
    ) {
        composable(route = DetailsScreenRoutes.AddProducts.route) {
            AddProductsScreen(
                navController = navController,
                bottomPadding = bottomPadding
            )
        }
    }
}

sealed class DetailsScreenRoutes(val route: String) {
    data object AddProducts: DetailsScreenRoutes("add_products")
}