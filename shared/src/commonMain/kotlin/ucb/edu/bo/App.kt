package ucb.edu.bo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ucb.edu.bo.core.navigation.AppNavHost

@Composable
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}