package ucb.edu.bo.earthquake.domain.repository

import ucb.edu.bo.earthquake.domain.model.EarthquakeModel

interface EarthquakeRepository {
    suspend fun getEarthquakes(): Result<List<EarthquakeModel>>
}
