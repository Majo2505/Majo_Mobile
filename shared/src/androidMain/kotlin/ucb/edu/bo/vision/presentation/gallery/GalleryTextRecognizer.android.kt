package ucb.edu.bo.vision.presentation.gallery

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

@Composable
actual fun rememberGalleryTextRecognizer(
    onImageSelected: (imageUri: String) -> Unit,
    onTextRecognized: (text: String) -> Unit,
    onError: (message: String) -> Unit
): () -> Unit {
    val context = LocalContext.current
    val currentOnImageSelected by rememberUpdatedState(onImageSelected)
    val currentOnText by rememberUpdatedState(onTextRecognized)
    val currentOnError by rememberUpdatedState(onError)

    // 1. Reconocedor de ML Kit (el mismo que usa la camara); se cierra al salir de la pantalla
    val recognizer = remember { TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) }
    DisposableEffect(Unit) {
        onDispose { recognizer.close() }
    }

    // 2. Photo Picker: selector de fotos del sistema. No necesita permiso de almacenamiento
    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        // Si el usuario cierra el selector sin elegir nada, uri llega null
        if (uri == null) return@rememberLauncherForActivityResult
        currentOnImageSelected(uri.toString())

        // 3. ML Kit: se crea la imagen desde la foto elegida y se busca el texto
        try {
            val image = InputImage.fromFilePath(context, uri)
            recognizer.process(image)
                .addOnSuccessListener { result -> currentOnText(result.text) }
                .addOnFailureListener { e -> currentOnError(e.message ?: "Error al leer la imagen") }
        } catch (e: Exception) {
            // fromFilePath puede fallar si no se puede abrir el archivo
            currentOnError(e.message ?: "No se pudo abrir la imagen")
        }
    }

    // 4. Lo que devuelve: una funcion que abre el selector mostrando solo imagenes
    return {
        pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}