package ucb.edu.bo.dollar.data.repository

import kotlinx.coroutines.flow.Flow
import ucb.edu.bo.dollar.data.datasource.DollarLocalDataSource
import ucb.edu.bo.dollar.data.datasource.RealTimeDataBase
import ucb.edu.bo.dollar.domain.model.DollarModel
import ucb.edu.bo.dollar.domain.repository.DollarRepository

class DollarRepositoryImpl(
    private val localDataSource: DollarLocalDataSource,
    private val realTimeDataBase: RealTimeDataBase
) : DollarRepository {

    override suspend fun getList(): List<DollarModel> {
        return localDataSource.getList()
    }

    override suspend fun insert(dollar: DollarModel) {
        localDataSource.insert(dollar)
    }

    override fun observeDollar(): Flow<String?> {
        return realTimeDataBase.observeMessage()
    }
}
