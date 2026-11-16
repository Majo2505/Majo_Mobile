package ucb.edu.bo.vision.presentation.state

data class VisionState(
    val recognizedText: String = "",
    val isPaused: Boolean = false
)