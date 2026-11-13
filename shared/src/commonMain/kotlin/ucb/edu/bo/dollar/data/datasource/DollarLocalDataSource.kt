package ucb.edu.bo.dollar.data.datasource

import ucb.edu.bo.dollar.data.dao.DollarDao
import ucb.edu.bo.dollar.data.entity.DollarEntity
import ucb.edu.bo.dollar.domain.model.DollarModel

class DollarLocalDataSource(
    private val dao: DollarDao
) {
    suspend fun getList(): List<DollarModel> {
        return dao.getList().map { it.toModel() }
    }

    suspend fun insert(dollar: DollarModel) {
        dao.insert(dollar.toEntity())
    }

    private fun DollarEntity.toModel(): DollarModel {
        return DollarModel(
            dollarOfficial = dollarOfficial,
            dollarParallel = dollarParallel,
            timestamp = timestamp
        )
    }

    private fun DollarModel.toEntity(): DollarEntity {
        return DollarEntity(
            dollarOfficial = dollarOfficial,
            dollarParallel = dollarParallel,
            timestamp = timestamp
        )
    }
}