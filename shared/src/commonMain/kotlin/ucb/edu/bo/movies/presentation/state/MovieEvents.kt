package ucb.edu.bo.movies.presentation.state

sealed interface MovieEvents {
    data object LoadMovies : MovieEvents
    data class OnMovieClicked(val id: String) : MovieEvents
}
