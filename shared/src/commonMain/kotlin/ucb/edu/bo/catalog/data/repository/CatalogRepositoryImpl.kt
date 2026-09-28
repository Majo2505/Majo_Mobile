package ucb.edu.bo.catalog.data.repository

import ucb.edu.bo.catalog.data.datasource.CatalogRemoteDataSource
import ucb.edu.bo.catalog.data.mapper.toDomain
import ucb.edu.bo.catalog.domain.model.CatalogMovieModel
import ucb.edu.bo.catalog.domain.repository.CatalogRepository

class CatalogRepositoryImpl(private val dataSource: CatalogRemoteDataSource) : CatalogRepository {
    override suspend fun getCatalogMovies(): List<CatalogMovieModel> {
        return try {
            val response = dataSource.getCatalogMovies()
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
