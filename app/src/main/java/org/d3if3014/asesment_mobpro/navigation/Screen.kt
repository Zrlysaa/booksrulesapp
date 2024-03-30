package org.d3if3014.asesment_mobpro.navigation

sealed class Screen (val route: String) {
    data object Splash: Screen("splashScreen")
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Option: Screen("optionScreen")
    data object Rules: Screen("rulesScreen")
}