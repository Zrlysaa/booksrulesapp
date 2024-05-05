package org.d3if3014.asesment_mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3014.asesment_mobpro.ui.screen.MainScreen
import org.d3if3014.asesment_mobpro.ui.theme.Screen.DetailScreen
import org.d3if3014.asesment_mobpro.ui.theme.Screen.KEY_ID_KRITIK
import org.d3if3014.asesment_mobpro.ui.theme.Screen.OptionScreen
import org.d3if3014.asesment_mobpro.ui.theme.Screen.RulesScreen
import org.d3if3014.asesment_mobpro.ui.theme.Screen.SplashScreen
import org.d3if3014.mobpro.ui.screen.AboutScreen



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
