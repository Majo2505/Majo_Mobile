package ucb.edu.bo.profile.domain.repository

import ucb.edu.bo.profile.domain.model.ProfileModel

interface ProfileRepository {
    suspend fun getProfile(): ProfileModel
}
