package ucb.edu.bo.moviedetail.presentation.state

sealed interface MovieDetailEvents {
    data class LoadDetail(val id: String) : MovieDetailEvents
    data object OnBackClicked : MovieDetailEvents
}
