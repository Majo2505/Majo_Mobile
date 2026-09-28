package ucb.edu.bo.catalog.data.datasource

import ucb.edu.bo.catalog.data.dto.CatalogDto

interface CatalogRemoteDataSource {
    suspend fun getCatalogMovies(): CatalogDto
}
