package ucb.edu.bo.push.domain.usecase

import ucb.edu.bo.push.domain.repository.PushTokenRepository

class UnregisterPushTokenUseCase(private val repository: PushTokenRepository) {
    suspend operator fun invoke(): Result<Unit> = repository.unregister()
}