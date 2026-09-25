package ucb.edu.bo.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ucb.edu.bo.signin.presentation.screen.SigninScreen
import ucb.edu.bo.signup.presentation.screen.SignUpScreen
import ucb.edu.bo.movies.presentation.screen.MovieScreen
import ucb.edu.bo.moviedetail.presentation.screen.MovieDetailScreen
import ucb.edu.bo.profile.presentation.screen.ProfileScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.Login) {
        composable<NavRoute.Login> {
            SigninScreen(navController = navController)
        }
        composable<NavRoute.SignUp> {
            SignUpScreen(navController = navController)
        }
        composable<NavRoute.Movies> {
            MovieScreen(navController = navController)
        }
        composable<NavRoute.MovieDetail> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<NavRoute.MovieDetail>()
            MovieDetailScreen(
                movieId = detailRoute.movieId,
                navController = navController
            )
        }
        composable<NavRoute.Profile> {
            ProfileScreen(navController = navController)
        }
        composable<NavRoute.ProfileEdit> {
            // Pantalla de Edición de Perfil
        }
    }
}
