package ucb.edu.bo.core.theme

import androidx.compose.runtime.staticCompositionLocalOf

// Lo que el usuario elige: seguir al sistema o forzar un modo
enum class ThemeChoice { SYSTEM, LIGHT, DARK, HIGH_CONTRAST }

// Tamano de fuente elegido por el usuario (se multiplica por el del sistema)
enum class FontSizeChoice(val scale: Float) {
    SMALL(0.85f),
    NORMAL(1.0f),
    LARGE(1.3f),
    EXTRA_LARGE(1.6f)
}

// Idioma elegido por el usuario (null = el idioma del telefono)
enum class LanguageChoice(val tag: String?) {
    SYSTEM(null),
    SPANISH("es"),
    ENGLISH("en")
}

// Permite leer y cambiar el tema y el tamano de fuente desde cualquier pantalla
class ThemeController(
    val choice: ThemeChoice,
    val onChoiceChange: (ThemeChoice) -> Unit,
    val fontSize: FontSizeChoice = FontSizeChoice.NORMAL,
    val onFontSizeChange: (FontSizeChoice) -> Unit = {},
    val language: LanguageChoice = LanguageChoice.SYSTEM,
    val onLanguageChange: (LanguageChoice) -> Unit = {}
)

val LocalThemeController = staticCompositionLocalOf {
    ThemeController(choice = ThemeChoice.SYSTEM, onChoiceChange = {})
}