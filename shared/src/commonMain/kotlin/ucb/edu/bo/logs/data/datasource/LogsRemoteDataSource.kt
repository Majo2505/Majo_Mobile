package ucb.edu.bo.logs.data.datasource

import ucb.edu.bo.logs.domain.model.LogModel

interface LogsRemoteDataSource {
    suspend fun send(logs: List<LogModel>): Result<Unit>
}