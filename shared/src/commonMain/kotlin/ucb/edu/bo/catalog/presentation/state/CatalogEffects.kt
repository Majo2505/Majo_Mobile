package ucb.edu.bo.catalog.presentation.state

sealed interface CatalogEffects {
    data class NavigateToDetail(val movieId: String) : CatalogEffects
}
