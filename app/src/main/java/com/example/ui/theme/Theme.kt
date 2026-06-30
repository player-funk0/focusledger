package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PureDarkColorScheme = darkColorScheme(
    primary = WhiteEmphasis,
    onPrimary = TrueBlack,
    secondary = MediumWhite,
    onSecondary = Charcoal,
    tertiary = SubduedGray,
    onTertiary = TrueBlack,
    background = TrueBlack,
    onBackground = WhiteEmphasis,
    surface = Charcoal,
    onSurface = MediumWhite,
    outline = SlateDivider,
    error = ErrorRed,
    onError = TrueBlack
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Force true black theme
    dynamicColor: Boolean = false, // Disable dynamic colors for exact design specs
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PureDarkColorScheme,
        typography = Typography,
        content = content
    )
}
