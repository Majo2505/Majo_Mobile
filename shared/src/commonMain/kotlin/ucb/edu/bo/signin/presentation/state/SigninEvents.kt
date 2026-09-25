package ucb.edu.bo.signin.presentation.state

sealed interface SigninEvents {
    data class OnEmailChanged(val value: String) : SigninEvents
    data class OnPasswordChanged(val value: String) : SigninEvents
    data object TogglePasswordVisibility : SigninEvents
    data object OnSubmit : SigninEvents
}
