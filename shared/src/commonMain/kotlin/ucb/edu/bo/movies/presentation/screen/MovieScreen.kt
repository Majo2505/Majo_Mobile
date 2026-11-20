package ucb.edu.bo.movies.presentation.screen

import org.jetbrains.compose.resources.stringResource
import majo.shared.generated.resources.*

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ucb.edu.bo.analytics.AnalyticsTracker
import ucb.edu.bo.core.navigation.NavRoute
import ucb.edu.bo.core.notifications.LocalNotifier
import ucb.edu.bo.core.survey.SurveyConfig
import ucb.edu.bo.movies.presentation.state.MovieEffects
import ucb.edu.bo.movies.presentation.state.MovieEvents
import ucb.edu.bo.movies.presentation.state.MovieViewModel
import ucb.edu.bo.remoteconfig.domain.model.AppConfig
import ucb.edu.bo.remoteconfig.domain.usecase.GetAppConfigUseCase
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MovieScreen(
    navController: NavHostController,
    viewModel: MovieViewModel = koinViewModel(),
    getAppConfig: GetAppConfigUseCase = koinInject(),
    analytics: AnalyticsTracker = koinInject()
) {
    val state by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    // Textos de la notificacion local (traducidos segun el idioma)
    val notifyTitle = stringResource(Res.string.notify_title)
    val notifyText = stringResource(Res.string.notify_text)

    // REMOTE CONFIG: valores remotos (mensaje de bienvenida y promo)
    var config by remember { mutableStateOf(AppConfig.DEFAULT) }

    // Boton del menu que se toco por ultimo (queda amarillo). Se guarda para que siga
    // marcado al volver de la otra pantalla. -1 = ninguno
    var selectedMenu by rememberSaveable { mutableStateOf(-1) }

    // SURVEYMONKEY: invitacion a la encuesta
    var showSurveyPrompt by remember { mutableStateOf(false) }

    // Se vuelve a leer cada 15 s mientras la pantalla esta abierta (igual que en Configuracion remota).
    // El LaunchedEffect se cancela solo al salir de la pantalla.
    LaunchedEffect(Unit) {
        while (true) {
            config = getAppConfig()
            delay(15_000L)
        }
    }

    // ANALYTICS: el usuario abrio la pantalla de peliculas
    LaunchedEffect(Unit) {
        analytics.logEvent("view_movies", emptyMap())
    }

    // SURVEYMONKEY: a los 8 segundos, una sola vez por sesion, invita a responder la encuesta
    LaunchedEffect(Unit) {
        if (!SurveyConfig.promptShown) {
            delay(8_000)
            SurveyConfig.promptShown = true
            showSurveyPrompt = true
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(MovieEvents.LoadMovies)
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is MovieEffects.NavigateToDetail -> {
                    navController.navigate(NavRoute.MovieDetail(effect.movieId))
                }
            }
        }
    }

    if (showSurveyPrompt) {
        AlertDialog(
            onDismissRequest = { showSurveyPrompt = false },
            title = { Text(stringResource(Res.string.survey_title)) },
            text = { Text(stringResource(Res.string.survey_text)) },
            confirmButton = {
                TextButton(onClick = {
                    showSurveyPrompt = false
                    analytics.logEvent("open_survey", mapOf("source" to "prompt"))
                    uriHandler.openUri(SurveyConfig.SURVEY_URL)
                }) {
                    Text(stringResource(Res.string.survey_answer))
                }
            },
            dismissButton = {
                TextButton(onClick = { showSurveyPrompt = false }) {
                    Text(stringResource(Res.string.survey_later))
                }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(Res.string.movies_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { navController.navigate(NavRoute.Profile) }) {
                Text(stringResource(Res.string.movies_profile))
            }

        }

        // Mensaje que viene de Remote Config
        // Si Firebase no manda un valor, se muestra el texto por defecto traducido
        Text(
            text = if (config.mensajeBienvenida == AppConfig.DEFAULT.mensajeBienvenida) {
                stringResource(Res.string.login_welcome)
            } else {
                config.mensajeBienvenida
            },
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Promo: solo se muestra si Remote Config lo activa
        if (config.mostrarPromo) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = stringResource(Res.string.movies_promo),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Menu principal: cada accion es un boton y se muestran en 2 columnas
        val menuActions: List<Pair<String, () -> Unit>> = listOf(
            // E25-5: notificacion local
            stringResource(Res.string.movies_notify) to { LocalNotifier.show(notifyTitle, notifyText) },
            stringResource(Res.string.movies_earthquake) to { navController.navigate(NavRoute.Earthquake) },
            stringResource(Res.string.movies_catalog) to { navController.navigate(NavRoute.Catalog) },
            stringResource(Res.string.movies_github) to { navController.navigate(NavRoute.UserInfo) },
            stringResource(Res.string.movies_dollar) to { navController.navigate(NavRoute.Dollar) },
            stringResource(Res.string.movies_vision) to { navController.navigate(NavRoute.Vision) },
            // Configuracion remota (antes estaba en Perfil)
            stringResource(Res.string.profile_remote_config) to { navController.navigate(NavRoute.Config) },
            // SURVEYMONKEY: boton fijo para abrir la encuesta cuando el usuario quiera
            stringResource(Res.string.movies_feedback) to {
                analytics.logEvent("open_survey", mapOf("source" to "button"))
                uriHandler.openUri(SurveyConfig.SURVEY_URL)
            }
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            menuActions.withIndex().chunked(2).forEach { rowActions ->
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    rowActions.forEach { (index, item) ->
                        val (label, action) = item
                        MenuButton(
                            text = label,
                            selected = index == selectedMenu,
                            onClick = {
                                selectedMenu = index
                                action()
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    // Si la fila queda con un solo boton, se rellena para que no ocupe todo el ancho
                    if (rowActions.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    Text(
                        text = stringResource(Res.string.common_error, state.error.toString()),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.movies) { movie ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // ANALYTICS: el usuario toco una pelicula
                                        analytics.logEvent(
                                            "movie_click",
                                            mapOf("movie_id" to movie.id)
                                        )
                                        viewModel.onEvent(MovieEvents.OnMovieClicked(movie.id))
                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            ) {
                                Text(
                                    text = movie.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private val MenuBlue = Color(0xFF1E88E5)
private val MenuYellow = Color(0xFFFFC107)
private val MenuDarkText = Color(0xFF212121)

// Boton del menu: azul normalmente y amarillo cuando se lo toca (y mientras se presiona)
@Composable
private fun MenuButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isYellow = selected || isPressed
    val containerColor by animateColorAsState(if (isYellow) MenuYellow else MenuBlue)
    val contentColor by animateColorAsState(if (isYellow) MenuDarkText else Color.White)

    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 0.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
        modifier = modifier.height(64.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}