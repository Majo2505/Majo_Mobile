package ucb.edu.bo.exchange.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ucb.edu.bo.exchange.data.datasource.ExchangeRateLocalDataSource
import ucb.edu.bo.exchange.domain.model.ExchangeRateModel

class ExchangeRateViewModel(
    private val dataSource: ExchangeRateLocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExchangeRateState())
    val uiState: StateFlow<ExchangeRateState> = _uiState.asStateFlow()

    private val _effect = Channel<ExchangeRateEffects>()
    val effect: Flow<ExchangeRateEffects> = _effect.receiveAsFlow()

    init {
        onEvent(ExchangeRateEvents.LoadRates)
    }

    fun onEvent(event: ExchangeRateEvents) {
        when (event) {
            is ExchangeRateEvents.LoadRates -> loadRates()
            is ExchangeRateEvents.SaveRate -> saveRate(event.official, event.parallel)
            is ExchangeRateEvents.SelectRate -> {}
        }
    }

    private fun loadRates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val list = dataSource.getList()
                _uiState.update { it.copy(isLoading = false, rates = list) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.localizedMessage ?: "Error al cargar") }
                _effect.send(ExchangeRateEffects.ShowToast("Error al cargar los datos"))
            }
        }
    }

    private fun saveRate(official: String, parallel: String) {
        viewModelScope.launch {
            try {
                val newRate = ExchangeRateModel(
                    id = 0,
                    officialRate = official,
                    parallelRate = parallel,
                    timestamp = System.currentTimeMillis()
                )
                dataSource.save(newRate)
                _effect.send(ExchangeRateEffects.ShowToast("Registrado con éxito"))
                loadRates()
            } catch (e: Exception) {
                _effect.send(ExchangeRateEffects.ShowToast("Error al guardar"))
            }
        }
    }
}
