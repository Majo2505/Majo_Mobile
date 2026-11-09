package ucb.edu.bo.dollar.domain.repository

import ucb.edu.bo.dollar.domain.model.DollarModel

interface DollarRepository {
    suspend fun getList(): List<DollarModel>
    suspend fun insert(dollar: DollarModel)
}