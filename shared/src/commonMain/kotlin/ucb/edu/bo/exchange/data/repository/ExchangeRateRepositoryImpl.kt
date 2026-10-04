package ucb.edu.bo.exchange.data.repository


import ucb.edu.bo.exchange.data.datasource.ExchangeRateDataSource
import ucb.edu.bo.exchange.domain.model.ExchangeRateModel
import ucb.edu.bo.exchange.domain.repository.ExchangeRateRepository

class ExchangeRateRepositoryImpl(val local: ExchangeRateDataSource
): ExchangeRateRepository {
    override suspend fun getList(): List<ExchangeRateModel> {
        return local.getList()
    }
    override suspend fun insert(dollar: ExchangeRateModel) {
        local.insert(dollar)
    }
}