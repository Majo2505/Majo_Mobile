package ucb.edu.bo.earthquake.data.repository

import ucb.edu.bo.earthquake.data.datasource.EarthquakeRemoteDataSource
import ucb.edu.bo.earthquake.data.mapper.toDomain
import ucb.edu.bo.earthquake.domain.model.EarthquakeModel
import ucb.edu.bo.earthquake.domain.repository.EarthquakeRepository

class EarthquakeRepositoryImpl(
    private val dataSource: EarthquakeRemoteDataSource
) : EarthquakeRepository {
    override suspend fun getEarthquakes(): Result<List<EarthquakeModel>> {
        return try {
            val response = dataSource.getEarthquakes()
            val list = response.features.map { it.toDomain() }
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
