package ucb.edu.bo.catalog.domain.repository

import ucb.edu.bo.catalog.domain.model.CatalogMovieModel

interface CatalogRepository {
    suspend fun getCatalogMovies(): List<CatalogMovieModel>
}
