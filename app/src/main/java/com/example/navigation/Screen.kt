package com.example.navigation

sealed class Screen(val route: String){
    object TrendingScreen: Screen(route = "home_screen")
    object TrendingScreenListing : Screen(route = "detail_screen")
}
