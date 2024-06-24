package org.d3if3014.asesment_mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3014.asesment_mobpro.ui.screen.DetailScreen
import org.d3if3014.asesment_mobpro.ui.screen.KEY_ID_KRITIK
import org.d3if3014.asesment_mobpro.ui.screen.OptionScreen
import org.d3if3014.asesment_mobpro.ui.screen.RulesScreen
import org.d3if3014.asesment_mobpro.ui.screen.SplashScreen
import org.d3if3014.asesment_mobpro.ui.screen.AboutScreen
import org.d3if3014.asesment_mobpro.ui.screen.ImageScreen
import org.d3if3014.asesment_mobpro.ui.screen.MainScreen

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
        composable("imageScreen") {
            ImageScreen(navController)
        }
        composable(Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route, arguments = listOf(
            navArgument(KEY_ID_KRITIK){ type = NavType.LongType}
        )
        ){navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_KRITIK)
            DetailScreen(navController, id)
        }
    }
}
