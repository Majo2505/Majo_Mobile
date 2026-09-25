package ucb.edu.bo.profile.presentation.state

import ucb.edu.bo.profile.domain.model.ProfileModel

data class ProfileState(
    val profile: ProfileModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
