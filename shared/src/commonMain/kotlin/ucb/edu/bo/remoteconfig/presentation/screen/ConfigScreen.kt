package ucb.edu.bo.remoteconfig.presentation.screen

import org.jetbrains.compose.resources.stringResource
import majo.shared.generated.resources.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.buttons.PrimaryButton
import edu.ucb.ds.components.cards.DsCard
import edu.ucb.ds.components.labels.DsLabel
import ucb.edu.bo.remoteconfig.domain.model.AppConfig
import ucb.edu.bo.remoteconfig.presentation.state.ConfigEvents
import ucb.edu.bo.remoteconfig.presentation.state.ConfigVM
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ConfigScreen(
    navController: NavHostController,
    viewModel: ConfigVM = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ConfigEvents.LoadConfig)
    }

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

        DsLabel(
            text = stringResource(Res.string.profile_remote_config)
        )

        if (state.isOffline) {
            DsLabel(
                text = stringResource(Res.string.config_offline)
            )
        }

        DsCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                DsLabel(
                    text = stringResource(
                        Res.string.config_message,
                        // Si Firebase no manda un valor, se muestra el texto por defecto traducido
                        if (state.welcomeMessage == AppConfig.DEFAULT.welcomeMessage) {
                            stringResource(Res.string.login_welcome)
                        } else {
                            state.welcomeMessage
                        }
                    )
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                DsLabel(
                    text = stringResource(Res.string.config_max_items, state.maxItems.toString())
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                DsLabel(
                    text = stringResource(
                        Res.string.config_feature,
                        stringResource(if (state.featureEnabled) Res.string.common_yes else Res.string.common_no)
                    )
                )
            }
        }

        if (state.isLoading) {
            DsLabel(
                text = stringResource(Res.string.common_loading)
            )
        }

        state.error?.let {
            DsLabel(
                text = stringResource(Res.string.common_error, it)
            )
        }

        PrimaryButton(
            text = stringResource(Res.string.config_refresh),
            onClick = {
                viewModel.onEvent(
                    ConfigEvents.RefreshConfig
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}