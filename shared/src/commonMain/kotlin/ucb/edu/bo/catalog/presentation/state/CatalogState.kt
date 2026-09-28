package ucb.edu.bo.catalog.presentation.state

import ucb.edu.bo.catalog.domain.model.CatalogMovieModel

data class CatalogState(
    val movies: List<CatalogMovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
