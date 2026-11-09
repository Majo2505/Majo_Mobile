package ucb.edu.bo.exchange.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import ucb.edu.bo.exchange.domain.model.ExchangeRateModel
import ucb.edu.bo.exchange.presentation.state.ExchangeRateEffects
import ucb.edu.bo.exchange.presentation.state.ExchangeRateEvents
import ucb.edu.bo.exchange.presentation.state.ExchangeRateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeRateScreen(viewModel: ExchangeRateViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ExchangeRateEffects.ShowToast -> snackbarHostState.showSnackbar(effect.message)
                is ExchangeRateEffects.NavigateBack -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopAppBar(title = { Text("Tipos de Cambio - MVI") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(ExchangeRateEvents.SaveRate("12.05", "12.08")) }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.rates.isEmpty() -> Text("No hay tasas registradas.")
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(uiState.rates) { rate ->
                            ExchangeRateItemCard(rate)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExchangeRateItemCard(rate: ExchangeRateModel) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: \", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Oficial: \", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Paralelo: \", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
