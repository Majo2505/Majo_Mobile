package ucb.edu.bo.moviedetail.presentation.state

sealed interface MovieDetailEffects {
    data object NavigateBack : MovieDetailEffects
}
