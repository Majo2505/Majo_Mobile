package ucb.edu.bo.logs.domain.repository

import ucb.edu.bo.logs.domain.model.LogModel

interface LogsRepository {
    suspend fun send(logs: List<LogModel>): Result<Unit>
}