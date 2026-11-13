package ucb.edu.bo.logs.data.service

import com.develoop.logs.ELogResultCode
import com.develoop.logs.GrpcLogServiceClient
import com.develoop.logs.LogRequest
import ucb.edu.bo.logs.data.datasource.LogsRemoteDataSource
import ucb.edu.bo.logs.data.mapper.toDto
import ucb.edu.bo.logs.domain.model.LogModel

class LogsService(baseUrl: String) : LogsRemoteDataSource {

    private val client = GrpcLogServiceClient(createGrpcClient(baseUrl))

    override suspend fun send(logs: List<LogModel>): Result<Unit> {
        return try {
            val request = LogRequest(logs = logs.map { it.toDto() })
            val response = client.send().execute(request)
            if (response.resultCode == ELogResultCode.LOG_RESULT_OK) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("El servidor respondio " + response.resultCode))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}