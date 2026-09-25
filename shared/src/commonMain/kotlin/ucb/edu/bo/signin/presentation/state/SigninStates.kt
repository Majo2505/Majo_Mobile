package ucb.edu.bo.signin.presentation.state

data class SigninStates(
    val userName: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false
)
