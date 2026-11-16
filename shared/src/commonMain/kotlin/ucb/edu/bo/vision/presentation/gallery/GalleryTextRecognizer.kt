package ucb.edu.bo.vision.presentation.gallery

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGalleryTextRecognizer(
    onImageSelected: (imageUri: String) -> Unit,
    onTextRecognized: (text: String) -> Unit,
    onError: (message: String) -> Unit
): () -> Unit