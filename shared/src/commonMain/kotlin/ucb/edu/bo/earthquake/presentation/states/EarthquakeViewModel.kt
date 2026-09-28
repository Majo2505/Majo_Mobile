package ucb.edu.bo.earthquake.presentation.states

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucb.edu.bo.earthquake.domain.usecase.GetEarthquakesUseCase

class EarthquakeViewModel(
    private val getEarthquakesUseCase: GetEarthquakesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(EarthquakeState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<EarthquakeEffects>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onEvent(event: EarthquakeEvents) {
        when (event) {
            is EarthquakeEvents.LoadEarthquakes -> {
                viewModelScope.launch {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                    getEarthquakesUseCase.invoke().fold(
                        onSuccess = { list ->
                            _uiState.update { it.copy(isLoading = false, earthquakes = list) }
                        },
                        onFailure = { error ->
                            _uiState.update { it.copy(isLoading = false, error = error.message ?: "Error desconocido") }
                        }
                    )
                }
            }
        }
    }
}
