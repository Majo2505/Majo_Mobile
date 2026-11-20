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
                _uiState.update {
                    it.copy(recognizedText = "", selectedImage = null, isProcessing = false, error = null)
                }
            }

            // Al cambiar de modo se empieza de cero
            is VisionEvents.OnModeChange -> {
                _uiState.update {
                    VisionState(mode = event.mode)
                }
            }
            // Se eligio una foto: se muestra y se espera a ML Kit
            is VisionEvents.OnImageSelected -> {
                _uiState.update {
                    it.copy(
                        selectedImage = event.imageUri,
                        recognizedText = "",
                        isProcessing = true,
                        error = null
                    )
                }
            }
            // ML Kit termino de leer la foto (puede venir vacio si no habia texto).
            // Si mientras tanto se limpio o se cambio de modo, el resultado ya no sirve
            is VisionEvents.OnGalleryResult -> {
                _uiState.update {
                    if (it.isWaitingGallery()) it.copy(recognizedText = event.text, isProcessing = false) else it
                }
            }
            is VisionEvents.OnGalleryError -> {
                _uiState.update {
                    if (it.isWaitingGallery()) it.copy(isProcessing = false, error = event.message) else it
                }
            }
        }
    }

    private fun VisionState.isWaitingGallery() = mode == VisionMode.GALLERY && selectedImage != null
}