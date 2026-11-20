package ucb.edu.bo.vision.presentation.camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import majo.shared.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import java.util.concurrent.Executors

@Composable
actual fun CameraTextScanner(
    onTextRecognized: (String) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> hasPermission = granted }

    LaunchedEffect(Unit) {
        if (!hasPermission) permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    if (!hasPermission) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(Res.string.vision_permission_needed))
            Button(onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
                Text(stringResource(Res.string.vision_permission_button))
            }
        }
        return
    }

    // 2. Vista de la camara (PreviewView de CameraX dentro de Compose)
    val currentOnText by rememberUpdatedState(onTextRecognized)
    val previewView = remember { PreviewView(context) }

    // 3. CameraX: Preview muestra la camara e ImageAnalysis pasa cada cuadro a ML Kit
    DisposableEffect(lifecycleOwner) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val analysisExecutor = Executors.newSingleThreadExecutor()
        val providerFuture = ProcessCameraProvider.getInstance(context)
        var cameraProvider: ProcessCameraProvider? = null
        // Si se sale de la pantalla antes de que la camara arranque, no se debe conectar
        var disposed = false

        providerFuture.addListener({
            if (disposed) return@addListener
            val provider = providerFuture.get()
            cameraProvider = provider

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val analysis = ImageAnalysis.Builder()
                // Si ML Kit va lento, se descartan cuadros viejos y se analiza solo el mas reciente
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
            analysis.setAnalyzer(analysisExecutor, TextAnalyzer(recognizer) { text -> currentOnText(text) })

            provider.unbindAll()
            provider.bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview, analysis)
        }, ContextCompat.getMainExecutor(context))

        // Al salir de la pantalla se libera la camara, el hilo y el reconocedor
        onDispose {
            disposed = true
            cameraProvider?.unbindAll()
            analysisExecutor.shutdown()
            recognizer.close()
        }
    }

    AndroidView(factory = { previewView }, modifier = modifier)
}

// 4. ML Kit: recibe un cuadro de la camara y devuelve el texto que encontro
private class TextAnalyzer(
    private val recognizer: TextRecognizer,
    private val onResult: (String) -> Unit
) : ImageAnalysis.Analyzer {

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage == null) {
            imageProxy.close()
            return
        }
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        recognizer.process(image)
            .addOnSuccessListener { result -> onResult(result.text) }
            .addOnCompleteListener { imageProxy.close() }
    }
}