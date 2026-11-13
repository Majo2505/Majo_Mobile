package ucb.edu.bo.core.locale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.key

// Idioma elegido dentro de la app (cada plataforma lo aplica a su manera).
// Basado en la documentacion oficial de Compose Multiplatform (Resource environment).
expect object LocalAppLocale {
    val current: String
        @Composable get

    @Composable
    infix fun provides(value: String?): ProvidedValue<*>
}

// Aplica el idioma a todo lo que este adentro.
// locale = null usa el idioma del telefono; key(...) vuelve a dibujar los textos al cambiarlo.
@Composable
fun AppEnvironment(locale: String?, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalAppLocale provides locale,
    ) {
        key(locale) {
            content()
        }
    }
}
