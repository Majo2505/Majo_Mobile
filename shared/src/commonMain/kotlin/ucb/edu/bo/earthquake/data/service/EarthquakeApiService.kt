package ucb.edu.bo.earthquake.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ucb.edu.bo.earthquake.data.datasource.EarthquakeRemoteDataSource
import ucb.edu.bo.earthquake.data.dto.EarthquakeResponseDto

class EarthquakeApiService : EarthquakeRemoteDataSource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getEarthquakes(): EarthquakeResponseDto {
        val response = client.get("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=5&limit=3")
        return response.body<EarthquakeResponseDto>()
    }
}
