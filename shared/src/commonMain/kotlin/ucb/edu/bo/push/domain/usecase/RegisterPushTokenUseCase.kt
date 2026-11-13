package ucb.edu.bo.push.domain.usecase

import ucb.edu.bo.push.domain.repository.PushTokenRepository

class RegisterPushTokenUseCase(private val repository: PushTokenRepository) {
    suspend operator fun invoke(userId: String, email: String): Result<Unit> =
        repository.register(userId, email)
}