package ucb.edu.bo.dollar.domain.usecase

import ucb.edu.bo.dollar.domain.model.DollarModel
import ucb.edu.bo.dollar.domain.repository.DollarRepository

class GetDollarListUseCase(
    private val repository: DollarRepository
) {
    suspend operator fun invoke(): List<DollarModel> {
        return repository.getList()
    }
}