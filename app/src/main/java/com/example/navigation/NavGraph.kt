package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigation.data.PropertyViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,viewModel: PropertyViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screen.TrendingScreen.route
    ){
        composable(
            route = Screen.TrendingScreen.route
        ){
            TrendingScreen(navController = navController,viewModel)
        }
        composable(
            route = Screen.TrendingScreenListing.route
        ){
            TrendingScreenListing(navController = navController, viewModel)
        }
    }
}