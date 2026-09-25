package ucb.edu.bo.profile.domain.usecase

import ucb.edu.bo.profile.domain.model.ProfileModel
import ucb.edu.bo.profile.domain.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(): ProfileModel = repository.getProfile()
}
