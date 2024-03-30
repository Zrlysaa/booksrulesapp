package org.d3if3014.asesment_mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3014.mobpro.ui.screen.AboutScreen
import org.d3if3014.mobpro.ui.screen.MainScreen

@Composable
fun SetUpnavGraph(navController: NavHostController = rememberNavController()){
    NavHost (
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
        composable(route = Screen.Splash.route){
            AboutScreen(navController)
        }
        composable(route = Screen.Option.route){
            AboutScreen(navController)
        }
    }
}