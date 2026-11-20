package ucb.edu.bo.vision.presentation.state

sealed interface VisionEvents {
    data class OnTextRecognized(val text: String) : VisionEvents
    object OnTogglePause : VisionEvents
    object OnClear : VisionEvents

    // Galeria
    data class OnModeChange(val mode: VisionMode) : VisionEvents
    data class OnImageSelected(val imageUri: String) : VisionEvents
    data class OnGalleryResult(val text: String) : VisionEvents
    data class OnGalleryError(val message: String) : VisionEvents
}