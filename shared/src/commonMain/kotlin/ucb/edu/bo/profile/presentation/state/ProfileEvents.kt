package ucb.edu.bo.profile.presentation.state

sealed interface ProfileEvents {
    data object LoadProfile : ProfileEvents
    data object OnLogout : ProfileEvents
}
