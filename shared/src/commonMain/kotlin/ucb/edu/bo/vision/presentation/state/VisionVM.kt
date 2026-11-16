package ucb.edu.bo.vision.presentation.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VisionVM : ViewModel() {
    private val _uiState = MutableStateFlow(VisionState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: VisionEvents) {
        when (event) {
            is VisionEvents.OnTextRecognized -> {
                // En pausa se congela el ultimo texto; si la camara no ve nada se mantiene el anterior
                if (!_uiState.value.isPaused && event.text.isNotBlank()) {
                    _uiState.update { it.copy(recognizedText = event.text) }
                }
            }
            VisionEvents.OnTogglePause -> {
                _uiState.update { it.copy(isPaused = !it.isPaused) }
            }
            VisionEvents.OnClear -> {
                _uiState.update { it.copy(recognizedText = "") }
            }
        }
    }
}