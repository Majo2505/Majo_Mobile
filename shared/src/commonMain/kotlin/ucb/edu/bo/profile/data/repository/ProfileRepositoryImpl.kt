package ucb.edu.bo.profile.data.repository

import ucb.edu.bo.profile.domain.model.ProfileModel
import ucb.edu.bo.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.delay

class ProfileRepositoryImpl : ProfileRepository {
    override suspend fun getProfile(): ProfileModel {
        delay(500)
        return ProfileModel(
            id = "1",
            name = "Maria Jose Sandoval",
            email = "mariajose@ucb.edu.bo"
        )
    }
}
