package ucb.edu.bo.movies.presentation.state

import ucb.edu.bo.movies.domain.model.MovieModel

data class MovieState(
    val movies: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
