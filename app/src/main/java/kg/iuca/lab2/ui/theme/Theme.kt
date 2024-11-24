package kg.iuca.lab2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,
    background = DarkGray,
    surface = Gray
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200,
    background = White,
    surface = LightGray
)

@Composable
fun Lab2Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
