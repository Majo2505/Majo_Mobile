package ucb.edu.bo.dollar.domain.usecase

import kotlinx.coroutines.flow.Flow
import ucb.edu.bo.dollar.domain.repository.DollarRepository

class ObserveDollarUseCase(
    private val repository: DollarRepository
) {
    operator fun invoke(): Flow<String?> {
        return repository.observeDollar()
    }
}
