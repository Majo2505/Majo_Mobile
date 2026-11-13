package ucb.edu.bo.logs.domain.usecase

import ucb.edu.bo.logs.domain.model.LogModel
import ucb.edu.bo.logs.domain.repository.LogsRepository

class SendLogsUseCase(
    private val repository: LogsRepository
) {
    suspend operator fun invoke(logs: List<LogModel>): Result<Unit> {
        return repository.send(logs)
    }
}