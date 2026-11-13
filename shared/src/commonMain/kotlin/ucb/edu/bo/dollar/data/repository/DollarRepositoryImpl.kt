package ucb.edu.bo.dollar.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeoutOrNull
import ucb.edu.bo.dollar.data.datasource.DollarFirestoreDataSource
import ucb.edu.bo.dollar.data.datasource.DollarLocalDataSource
import ucb.edu.bo.dollar.data.datasource.RealTimeDataBase
import ucb.edu.bo.dollar.domain.model.DollarModel
import ucb.edu.bo.dollar.domain.repository.DollarRepository
import kotlin.time.Clock

// Room es la fuente principal (funciona sin internet); Firestore es la copia en la nube
class DollarRepositoryImpl(
    private val localDataSource: DollarLocalDataSource,
    private val realTimeDataBase: RealTimeDataBase,
    private val remoteDataSource: DollarFirestoreDataSource
) : DollarRepository {

    override suspend fun getList(): List<DollarModel> {
        return localDataSource.getList()
    }

    override suspend fun insert(dollar: DollarModel) {
        // El timestamp identifica el registro en Room y en Firestore
        val record = if (dollar.timestamp == 0L) {
            dollar.copy(timestamp = Clock.System.now().toEpochMilliseconds())
        } else {
            dollar
        }
        // 1. Guardar en Room
        localDataSource.insert(record)
        // 2. Subir a Firestore (si no hay internet, no bloquea; Firestore lo envia despues)
        runCatching { withTimeoutOrNull(5_000) { remoteDataSource.save(record) } }
    }

    override fun observeDollar(): Flow<String?> {
        return realTimeDataBase.observeMessage()
    }

    // Une los datos de Room y Firestore comparando por timestamp
    override suspend fun sync() {
        runCatching {
            withTimeoutOrNull(15_000) {
                val remote = remoteDataSource.getAll()
                val local = localDataSource.getList().filter { it.timestamp != 0L }
                val localKeys = local.map { it.timestamp }.toSet()
                val remoteKeys = remote.map { it.timestamp }.toSet()

                // Lo que esta en la nube y falta en Room -> se guarda en Room
                remote.filter { it.timestamp !in localKeys }
                    .forEach { localDataSource.insert(it) }

                // Lo que esta en Room y falta en la nube -> se sube a Firestore
                local.filter { it.timestamp !in remoteKeys }
                    .forEach { remoteDataSource.save(it) }
            }
        }
    }
}
