package ucb.edu.bo.earthquake.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel
import ucb.edu.bo.earthquake.presentation.states.EarthquakeEvents
import ucb.edu.bo.earthquake.presentation.states.EarthquakeViewModel

@Composable
fun EarthquakeScreen(
    navController: NavHostController,
    viewModel: EarthquakeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(EarthquakeEvents.LoadEarthquakes)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        TextButton(onClick = { navController.popBackStack() }) {
            Text("< Volver")
        }

        Text(
            text = "Terremotos (USGS)",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Box(modifier = Modifier.fillMaxSize().weight(1f)) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    Text(
                        text = "Error: ${state.error}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.earthquakes) { eq ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text(text = "Ubicación: ${eq.place}", fontWeight = FontWeight.Bold)
                                    Text(text = "Magnitud: ${eq.magnitude}")
                                    Text(text = "Fecha y Hora: ${eq.time} ")
                                    Text(text = "Enlace al evento: ${eq.url} ")
                                    Text(text = "Lat: ${eq.latitude}, Lon: ${eq.longitude}, Profundidad: ${eq.depth} km")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
