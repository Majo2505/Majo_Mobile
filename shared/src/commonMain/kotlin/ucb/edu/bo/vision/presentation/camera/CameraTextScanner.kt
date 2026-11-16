package ucb.edu.bo.vision.presentation.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CameraTextScanner(
    onTextRecognized: (String) -> Unit,
    modifier: Modifier = Modifier
)