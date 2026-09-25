package ucb.edu.bo.signin.presentation.state

data class SigninState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
