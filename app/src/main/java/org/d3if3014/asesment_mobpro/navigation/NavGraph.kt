package org.d3if3014.asesment_mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3014.asesment_mobpro.ui.theme.Screen.OptionScreen
import org.d3if3014.asesment_mobpro.ui.theme.Screen.RulesScreen
import org.d3if3014.asesment_mobpro.ui.theme.Screen.SplashScreen
import org.d3if3014.mobpro.ui.screen.AboutScreen
import org.d3if3014.mobpro.ui.screen.MainScreen

@Composable
fun SetUpnavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ) {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("aboutScreen") {
            AboutScreen(navController)
        }
        composable("splashScreen") {
            SplashScreen(navController)
        }
        composable("optionScreen") {
            OptionScreen(navController)
        }
        composable("rulesScreen") {
            RulesScreen(navController)
        }
    }
}
