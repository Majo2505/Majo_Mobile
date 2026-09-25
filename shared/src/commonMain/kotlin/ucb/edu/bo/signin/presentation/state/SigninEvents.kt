package ucb.edu.bo.signin.presentation.state

sealed interface SigninEvents {
    data class OnEmailChanged(val email: String) : SigninEvents
    data class OnPasswordChanged(val password: String) : SigninEvents
    data object OnLoginClicked : SigninEvents
    data object OnSignUpClicked : SigninEvents
}
