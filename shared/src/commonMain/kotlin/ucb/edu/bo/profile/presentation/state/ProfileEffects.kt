package ucb.edu.bo.profile.presentation.state

sealed interface ProfileEffects {
    data object NavigateToLogin : ProfileEffects
}
