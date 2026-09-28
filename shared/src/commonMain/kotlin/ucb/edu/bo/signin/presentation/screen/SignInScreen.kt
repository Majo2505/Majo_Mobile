package ucb.edu.bo.signin.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ucb.edu.bo.core.navigation.NavRoute
import ucb.edu.bo.signin.presentation.state.SigninEffects
import ucb.edu.bo.signin.presentation.state.SigninEvents
import ucb.edu.bo.signin.presentation.state.SigninViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SigninScreen(
    navController: NavHostController,
    viewModel: SigninViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is SigninEffects.NavigateToHome -> {
                    navController.navigate(NavRoute.Movies) {
                        popUpTo(NavRoute.Login) { inclusive = true }
                    }
                }
                is SigninEffects.NavigateToSignUp -> {
                    navController.navigate(NavRoute.SignUp)
                }
                is SigninEffects.ShowError -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 32.dp)
        )
        Text(
            text = "mgr si esta probando , el iniciar sesion ponga cuqlquier caracter en correo y contraseña y funciona, me falta algunas cosas en la parte de authentication ",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraLight,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(SigninEvents.OnEmailChanged(it)) },
            label = { Text("Correo Electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(SigninEvents.OnPasswordChanged(it)) },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (state.error != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Text(
                    text = "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Button(
            onClick = { viewModel.onEvent(SigninEvents.OnLoginClicked) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(4.dp))
            } else {
                Text("Ingresar")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("¿No tienes una cuenta?")
            TextButton(onClick = { viewModel.onEvent(SigninEvents.OnSignUpClicked) }) {
                Text("Regístrate")
            }
        }
    }
}
