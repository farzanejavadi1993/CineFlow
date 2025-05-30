package com.fermer.cineflow.presentation.main

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fermer.movie.presentation.detail.MovieDetailScreen
import com.fermer.cineflow.presentation.home.HomeScreen
import com.fermer.cineflow.presentation.splash.SplashScreen
import com.fermer.common.theme.ThemePreferenceManager
import kotlinx.serialization.json.Json

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               themeManager: ThemePreferenceManager) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("home") {
            HomeScreen(navController,themeManager)
        }

        composable(
            "movie_detail/{movieArgs}",
            arguments = listOf(navArgument("movieArgs") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("movieArgs") ?: return@composable
            val movieArgs = Json.decodeFromString<com.fermer.model.Movie>(Uri.decode(json))

            MovieDetailScreen(navController,movie = movieArgs)
        }

    }
}
