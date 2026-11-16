package ucb.edu.bo.vision.presentation.screen

import org.jetbrains.compose.resources.stringResource
import majo.shared.generated.resources.*

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ucb.edu.bo.vision.presentation.camera.CameraTextScanner
import ucb.edu.bo.vision.presentation.state.VisionEvents
import ucb.edu.bo.vision.presentation.state.VisionVM
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VisionScreen(
    navController: NavHostController,
    viewModel: VisionVM = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(onClick = { navController.popBackStack() }) {
            Text(stringResource(Res.string.common_back))
        }

        Text(
            text = stringResource(Res.string.vision_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        // Camara en vivo: cada texto que ML Kit encuentra se manda al ViewModel
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
        ) {
            CameraTextScanner(
                onTextRecognized = { text -> viewModel.onEvent(VisionEvents.OnTextRecognized(text)) },
                modifier = Modifier.fillMaxSize()
            )
        }

        // Resultado: el ultimo texto reconocido
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .heightIn(max = 160.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(Res.string.vision_result),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.recognizedText.ifBlank { stringResource(Res.string.vision_hint) },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { viewModel.onEvent(VisionEvents.OnTogglePause) },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    stringResource(if (state.isPaused) Res.string.vision_resume else Res.string.vision_pause)
                )
            }
            OutlinedButton(
                onClick = { viewModel.onEvent(VisionEvents.OnClear) },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(Res.string.vision_clear))
            }
        }
    }
}