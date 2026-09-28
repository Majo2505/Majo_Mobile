package ucb.edu.bo.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {
    @Serializable
    data object Login : NavRoute()

    @Serializable
    data object SignUp : NavRoute()
    @Serializable
    data object Earthquake : NavRoute()

    @Serializable
    data object UserInfo : NavRoute()

    @Serializable
    data object Movies : NavRoute()

    @Serializable
    data object Catalog : NavRoute()

    @Serializable
    data class MovieDetail(val movieId: String) : NavRoute()

    @Serializable
    data object Profile : NavRoute()


}
