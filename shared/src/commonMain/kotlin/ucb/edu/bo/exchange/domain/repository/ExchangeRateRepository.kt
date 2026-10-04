package ucb.edu.bo.exchange.domain.repository

import ucb.edu.bo.exchange.domain.model.ExchangeRateModel

interface ExchangeRateRepository {
    suspend fun getList(): List<ExchangeRateModel>

    suspend fun insert(dollar: ExchangeRateModel)
}