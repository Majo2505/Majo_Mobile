package ucb.edu.bo.catalog.domain.usecase

import ucb.edu.bo.catalog.domain.model.CatalogMovieModel
import ucb.edu.bo.catalog.domain.repository.CatalogRepository

class GetCatalogMoviesUseCase(private val repository: CatalogRepository) {
    suspend operator fun invoke(): List<CatalogMovieModel> {
        return repository.getCatalogMovies()
    }
}
