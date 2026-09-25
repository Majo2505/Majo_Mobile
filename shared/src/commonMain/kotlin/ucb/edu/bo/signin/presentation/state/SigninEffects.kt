package ucb.edu.bo.signin.presentation.state

sealed interface SigninEffects {
    data object NavigateToHome : SigninEffects
    data class ShowToast(val message: String) : SigninEffects
    data class ShowError(val message: String) : SigninEffects
}
