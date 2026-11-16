package ucb.edu.bo.vision.presentation.state

// De donde sale la imagen que se analiza
enum class VisionMode {
    CAMERA,
    GALLERY
}

data class VisionState(
    val mode: VisionMode = VisionMode.CAMERA,
    val recognizedText: String = "",
    val isPaused: Boolean = false,
    val selectedImage: String? = null,
    val isProcessing: Boolean = false,
    val error: String? = null
)