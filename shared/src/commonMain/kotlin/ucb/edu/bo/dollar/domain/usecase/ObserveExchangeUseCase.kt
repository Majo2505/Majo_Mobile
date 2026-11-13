package ucb.edu.bo.dollar.domain.usecase

import ucb.edu.bo.dollar.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow

class ObserveExchangeUseCase(
    val repository: ExchangeRepository
) {
    suspend fun invoke(): Flow<String?> {
        return repository.observe()
    }
}