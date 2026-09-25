package ucb.edu.bo.movies.presentation.state

sealed interface MovieEffects {
    data class NavigateToDetail(val movieId: String) : MovieEffects
}
