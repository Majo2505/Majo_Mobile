package ucb.edu.bo.vision.presentation.state

sealed interface VisionEvents {
    data class OnTextRecognized(val text: String) : VisionEvents
    object OnTogglePause : VisionEvents
    object OnClear : VisionEvents
}