package ucb.edu.bo.moviedetail.presentation.state

import ucb.edu.bo.moviedetail.domain.model.MovieDetailModel

data class MovieDetailState(
    val detail: MovieDetailModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
