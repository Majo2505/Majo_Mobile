package ucb.edu.bo.exchange.data.datasource

import ucb.edu.bo.exchange.data.dao.ExchangeRateDao
import ucb.edu.bo.exchange.data.entity.ExchangeRateEntity
import ucb.edu.bo.exchange.domain.model.ExchangeRateModel

class ExchangeRateDataSource(
    val dao: ExchangeRateDao
) {
    suspend fun getList(): List<ExchangeRateModel> {
        return dao.getList().map {
            it.toModel()
        }
    }
    suspend fun insert(exchangeRate: ExchangeRateModel) {
        dao.insert(exchangeRate.toEntity())
    }
    suspend fun getById(id: String): ExchangeRateModel? {
        return dao.getById(id)?.toModel()
    }
    private fun ExchangeRateEntity.toModel(): ExchangeRateModel {
        return ExchangeRateModel(
            official = officialRate ?: "",
            parallel = parallelRate ?: "",
        )
    }
    private fun ExchangeRateModel.toEntity() = ExchangeRateEntity(
        officialRate = "12.05",
        parallelRate = "12.07"
    )

}