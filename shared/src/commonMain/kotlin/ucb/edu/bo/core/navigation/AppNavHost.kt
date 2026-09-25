package ucb.edu.bo.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ucb.edu.bo.signin.presentation.screen.SigninScreen
import ucb.edu.bo.movies.presentation.screen.MovieScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.Login) {
        composable<NavRoute.Login> {
            SigninScreen(navController = navController)
        }
        composable<NavRoute.Movies> {
            MovieScreen(navController = navController)
        }
        composable<NavRoute.Profile> {
            // Aquí agregarás tu pantalla de Profile cuando la diseñes
        }
        composable<NavRoute.ProfileEdit> {
            // Aquí agregarás tu pantalla de ProfileEdit
        }
    }
}
