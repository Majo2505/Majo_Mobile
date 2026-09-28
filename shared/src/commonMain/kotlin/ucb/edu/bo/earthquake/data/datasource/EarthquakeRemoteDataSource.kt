package ucb.edu.bo.earthquake.data.datasource

import ucb.edu.bo.earthquake.data.dto.EarthquakeResponseDto

interface EarthquakeRemoteDataSource {
    suspend fun getEarthquakes(): EarthquakeResponseDto
}
