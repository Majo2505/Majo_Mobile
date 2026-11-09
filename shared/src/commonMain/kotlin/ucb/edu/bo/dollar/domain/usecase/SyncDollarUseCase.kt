package ucb.edu.bo.dollar.domain.usecase

import ucb.edu.bo.dollar.domain.repository.DollarRepository

class SyncDollarUseCase(
    private val repository: DollarRepository
) {
    suspend operator fun invoke() {
        repository.sync()
    }
}