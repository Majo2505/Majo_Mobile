package ucb.edu.bo.logs.data.repository

import ucb.edu.bo.logs.data.datasource.LogsRemoteDataSource
import ucb.edu.bo.logs.domain.model.LogModel
import ucb.edu.bo.logs.domain.repository.LogsRepository

class LogsRepositoryImpl(
    private val remoteDataSource: LogsRemoteDataSource
) : LogsRepository {
    override suspend fun send(logs: List<LogModel>): Result<Unit> {
        return remoteDataSource.send(logs)
    }
}