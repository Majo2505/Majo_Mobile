package ucb.edu.bo.dollar.data.repository

import ucb.edu.bo.dollar.data.datasource.RealTimeDataBase
import ucb.edu.bo.dollar.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow

class ExchangeRepositoryImpl(
    val realTimeDataBase: RealTimeDataBase
) : ExchangeRepository {
    override suspend fun observe(): Flow<String?> {
        return realTimeDataBase.observeMessage()
    }
}