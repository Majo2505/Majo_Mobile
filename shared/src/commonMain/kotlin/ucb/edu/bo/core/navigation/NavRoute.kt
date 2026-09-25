package ucb.edu.bo.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {
    @Serializable
    data object Login : NavRoute()

    @Serializable
    data object Movies : NavRoute()

    @Serializable
    data class MovieDetail(val movieId: String) : NavRoute()

    @Serializable
    data object Profile : NavRoute()

    @Serializable
    data object ProfileEdit : NavRoute()
}
