package org.d3if3014.asesment_mobpro.navigation

import org.d3if3014.asesment_mobpro.ui.theme.Screen.KEY_ID_KRITIK

sealed class Screen (val route: String) {
    data object Splash: Screen("splashScreen")
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Option: Screen("optionScreen")
    data object Rules: Screen("rulesScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_KRITIK}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}