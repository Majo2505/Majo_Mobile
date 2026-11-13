package ucb.edu.bo.dollar.presentation.state

sealed interface DollarEffects {
    data class ShowError(val message: String) : DollarEffects
}