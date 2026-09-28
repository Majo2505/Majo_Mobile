package ucb.edu.bo.catalog.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ucb.edu.bo.catalog.data.datasource.CatalogRemoteDataSource
import ucb.edu.bo.catalog.data.dto.CatalogDto

class CatalogApiService : CatalogRemoteDataSource {
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

    override suspend fun getCatalogMovies(): CatalogDto {
        val response = client.get("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fa3e844ce31744388e07fa47c7c5d8c3")
        return response.body<CatalogDto>()
    }
}
