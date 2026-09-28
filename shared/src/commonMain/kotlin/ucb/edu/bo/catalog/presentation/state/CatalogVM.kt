package ucb.edu.bo.catalog.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ucb.edu.bo.catalog.domain.usecase.GetCatalogMoviesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogVM(private val getCatalogMoviesUseCase: GetCatalogMoviesUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(CatalogState())
    val uiState: StateFlow<CatalogState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CatalogEffects>()
    val uiEffect: SharedFlow<CatalogEffects> = _uiEffect.asSharedFlow()

    fun onEvent(event: CatalogEvents) {
        when (event) {
            is CatalogEvents.LoadCatalog -> fetchCatalog()
            is CatalogEvents.OnMovieClicked -> {
                viewModelScope.launch {
                    _uiEffect.emit(CatalogEffects.NavigateToDetail(event.movieId))
                }
            }
        }
    }

    private fun fetchCatalog() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val list = getCatalogMoviesUseCase()
                _uiState.update { it.copy(movies = list, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
