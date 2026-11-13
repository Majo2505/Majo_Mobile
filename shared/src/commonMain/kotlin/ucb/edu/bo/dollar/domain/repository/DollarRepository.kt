package ucb.edu.bo.dollar.domain.repository

import kotlinx.coroutines.flow.Flow
import ucb.edu.bo.dollar.domain.model.DollarModel

interface DollarRepository {
    suspend fun getList(): List<DollarModel>
    suspend fun insert(dollar: DollarModel)
    fun observeDollar(): Flow<String?>
    suspend fun sync()
}
