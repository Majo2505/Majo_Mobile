package ucb.edu.bo.signin.presentation.state

sealed interface SigninEffects {
    data object NavigateToHome : SigninEffects
    data object NavigateToSignUp : SigninEffects
    data class ShowError(val message: String) : SigninEffects
}
