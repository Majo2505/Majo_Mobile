package ucb.edu.bo.push.domain.usecase

import ucb.edu.bo.push.domain.repository.PushTokenRepository

class RefreshPushTokenUseCase(private val repository: PushTokenRepository) {
    suspend operator fun invoke(newToken: String): Result<Unit> = repository.refresh(newToken)
}