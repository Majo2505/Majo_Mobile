package ucb.edu.bo.dollar.domain.repository

import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    suspend fun observe(): Flow<String?>
}