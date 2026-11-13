package ucb.edu.bo.dollar.domain.usecase

import ucb.edu.bo.dollar.domain.model.DollarModel
import ucb.edu.bo.dollar.domain.repository.DollarRepository

class InsertDollarUseCase(
    private val repository: DollarRepository
) {
    suspend operator fun invoke(dollar: DollarModel) {
        repository.insert(dollar)
    }
}